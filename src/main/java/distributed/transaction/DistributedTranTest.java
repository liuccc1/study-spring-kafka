package distributed.transaction;

import distributed.transaction.model.User;
import distributed.transaction.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 用kafka实现分布式事务
 *
 * @author Dean
 * @see "<a href="https://www.cnblogs.com/520playboy/p/6715438.html">使用事件和消息队列实现分布式事务</a>"
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Application.class)
public class DistributedTranTest {

	@Resource
	private UserService userService;

	@Test
	public void addUser() {
		int userCount = 10;
		for (int i = 0; i < userCount; i++) {
			User user = new User("foo" + i);
			userService.addUser(user);
		}
	}
}
