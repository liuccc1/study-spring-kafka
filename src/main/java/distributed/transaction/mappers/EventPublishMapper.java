package distributed.transaction.mappers;

import distributed.transaction.model.EventPublish;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Dean
 */
public interface EventPublishMapper {

	void save(EventPublish eventPublish);

	List<EventPublish> list(Map<String, Object> param);

	void updateStatus(@Param("ids") List<Long> ids, @Param("status") String status);
}
