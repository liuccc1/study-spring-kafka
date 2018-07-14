package distributed.transaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 入口类
 *
 * @author Dean
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("distributed.transaction.mappers")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
