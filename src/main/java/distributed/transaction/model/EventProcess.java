package distributed.transaction.model;

import com.google.common.base.MoreObjects;
import distributed.transaction.utils.EventProcessStatus;
import distributed.transaction.utils.EventType;

/**
 * 事件处理记录
 *
 * @author Dean
 */
public class EventProcess {
	private Long id;
	private String payload;

	/**
	 * 事件类型
	 */
	private EventType eventType;

	/**
	 * 事件处理状态
	 */
	private EventProcessStatus status;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("payload", payload)
				.add("eventType", eventType)
				.add("status", status)
				.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public EventProcessStatus getStatus() {
		return status;
	}

	public void setStatus(EventProcessStatus status) {
		this.status = status;
	}
}
