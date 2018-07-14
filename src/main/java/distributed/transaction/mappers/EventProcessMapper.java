package distributed.transaction.mappers;

import distributed.transaction.model.EventProcess;

/**
 * @author Dean
 */
public interface EventProcessMapper {
	void save(EventProcess eventProcess);
}
