package distributed.transaction.schedule;

import distributed.transaction.service.EventPublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时器
 *
 * @author Dean
 */
@Component
public class EventPublishSchedule {

	private final static Logger LOGGER = LoggerFactory.getLogger(EventPublishSchedule.class);

	@Resource
	private EventPublishService eventPublishService;

	/**
	 * 每N毫秒执行一次
	 */
	@Scheduled(fixedRate = 5000)
	public void publish() {
		LOGGER.debug("EventPublishSchedule execute.");
		eventPublishService.publish();
	}
}
