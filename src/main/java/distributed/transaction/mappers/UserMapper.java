package distributed.transaction.mappers;

import distributed.transaction.model.User;

/**
 * @author Dean
 */
public interface UserMapper {

	void save(User user);
}
