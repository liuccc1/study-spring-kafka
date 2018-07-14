package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Kafka原生API
 * 所有Properties参数含义可以参考官网Producer Configs和Consumer Configs 以及《Kafka权威指南》
 *
 * @author Dean
 */
public class KafkaTest {

	@Test
	public void produce() {
		String topic = "myTopic1";
		Properties props = new Properties();
		//必需的3个参数
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");

		props.put("acks", "all");
		props.put("retries", 0);

		//KafkaProducer是线程安全的，多个线程使用同一个实例效率更高，见KafkaProducer的源码注释
		Producer<String, String> producer = new KafkaProducer<>(props);

		//topic如果不存在会自动创建，topic会保存在zookeeper的znode中
		//忽略send()方法返回的Future对象，代表不关心消息是否发送成功
		producer.send(new ProducerRecord<>(topic, "a1", "b1"));

		//同步发送消息并等待结果返回
		Future future = producer.send(new ProducerRecord<>(topic, "a2", "b2"));
		try {
			future.get();
		} catch (Exception e) {
			//出现异常代表服务器端发生错误
			e.printStackTrace();
		}

		//异步发送消息
		producer.send(new ProducerRecord<>(topic, "a2", "b2"), (metadata, exception) -> {
			//发送消息出现异常
			if (exception != null) {
				log(exception);
			} else {
				log("消息发送成功了");
			}
		});
		producer.close();
	}

	@Test
	public void consume() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "myGroup1");
		props.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("myTopic1", "myTopic2"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s%n",
						record.offset(),
						record.key(),
						record.value());
			}
		}

	}

	private void log(Object object) {
		System.out.println(object);
	}
}