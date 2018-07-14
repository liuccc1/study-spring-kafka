package distributed.transaction.model;

import com.google.common.base.MoreObjects;
import distributed.transaction.utils.EventPublishStatus;
import distributed.transaction.utils.EventType;

/**
 * 事件发布记录
 *
 * @author Dean
 */
public class EventPublish {

	private Long id;
	/**
	 * 事件内容，保存发送到消息队列的json字符串
	 * payload单词含义：有效载荷，在计算机中代表一个数据包或者其它传输单元中运载的基本必要数据
	 */
	private String payload;

	/**
	 * 事件类型
	 */
	private EventType eventType;

	/**
	 * 事件发布状态
	 */
	private EventPublishStatus status;

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

	public EventPublishStatus getStatus() {
		return status;
	}

	public void setStatus(EventPublishStatus status) {
		this.status = status;
	}
}
