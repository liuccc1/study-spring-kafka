package distributed.transaction.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import distributed.transaction.mappers.EventProcessMapper;
import distributed.transaction.model.EventProcess;
import distributed.transaction.utils.EventProcessStatus;
import distributed.transaction.utils.EventType;
import distributed.transaction.utils.KafkaUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Dean
 */
@Service
public class EventProcessService {

	@Resource
	private EventProcessMapper eventProcessMapper;

	@PostConstruct
	public void init() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("MqMessageConsumeThread-%d")
				.setDaemon(true)
				.build();
		ExecutorService executorService = Executors.newSingleThreadExecutor(threadFactory);
		executorService.execute(new MqMessageConsumeThread());
	}

	private class MqMessageConsumeThread implements Runnable {
		@Override
		public void run() {
			KafkaUtils.consume(consumerRecord -> {
				EventProcess eventProcess = new EventProcess();
				eventProcess.setPayload(consumerRecord.value());
				eventProcess.setEventType(EventType.USER_CREATED);
				eventProcess.setStatus(EventProcessStatus.NEW);
				eventProcessMapper.save(eventProcess);
			});
		}
	}
}
