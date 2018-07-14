package distributed.transaction.service;

import com.google.gson.Gson;
import distributed.transaction.mappers.EventPublishMapper;
import distributed.transaction.mappers.UserMapper;
import distributed.transaction.model.EventPublish;
import distributed.transaction.model.User;
import distributed.transaction.utils.EventPublishStatus;
import distributed.transaction.utils.EventType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Dean
 */
@Service
public class UserService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private EventPublishMapper eventPublishMapper;

	@Transactional(rollbackFor = Exception.class)
	public void addUser(User user) {
		userMapper.save(user);

		EventPublish eventPublish = new EventPublish();
		eventPublish.setEventType(EventType.USER_CREATED);
		eventPublish.setPayload(new Gson().toJson(user));
		eventPublish.setStatus(EventPublishStatus.NEW);
		eventPublishMapper.save(eventPublish);
	}
}
