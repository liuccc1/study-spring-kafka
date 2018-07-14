package distributed.transaction.service;

import com.google.common.collect.Maps;
import distributed.transaction.mappers.EventPublishMapper;
import distributed.transaction.model.EventPublish;
import distributed.transaction.utils.EventPublishStatus;
import distributed.transaction.utils.KafkaUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Dean
 */
@Service
public class EventPublishService {

	private final static Logger LOGGER = LoggerFactory.getLogger(EventPublishService.class);

	@Resource
	private EventPublishMapper eventPublishMapper;

	@Transactional(rollbackFor = Exception.class)
	public void publish() {
		//查询所有状态为NEW的事件
		Map<String, Object> params = Maps.newHashMap();
		params.put("status", EventPublishStatus.NEW.name());
		List<EventPublish> eventPublishes = eventPublishMapper.list(params);
		if (!CollectionUtils.isEmpty(eventPublishes)) {
			//发送消息队列
			List<Long> ids = sendEventPublish(eventPublishes);
			if (!CollectionUtils.isEmpty(ids)) {
				//更新状态为PUBLISHED
				eventPublishMapper.updateStatus(ids, EventPublishStatus.PUBLISHED.name());
			}
		}
	}

	/**
	 * 发送EventPublish到消息队列
	 *
	 * @param eventPublishes EventPublish对象集合
	 * @return 发送成功的EventPublish的ID集合
	 */
	private static List<Long> sendEventPublish(List<EventPublish> eventPublishes) {
		if (CollectionUtils.isEmpty(eventPublishes)) {
			return Collections.emptyList();
		}
		List<Long> ids = Lists.newArrayList();
		for (EventPublish eventPublish : eventPublishes) {
			try {
				KafkaUtils.sendSync(eventPublish.getEventType().name(), eventPublish.getPayload());
				ids.add(eventPublish.getId());
			} catch (Exception e) {
				LOGGER.error("发送Kafka消息失败，eventPublish={}", eventPublish, e);
			}
		}
		LOGGER.debug("发送Kafka消息成功，ids={}", ids);
		return ids;
	}
}
