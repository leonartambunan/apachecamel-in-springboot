package id.co.nio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisEipApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisEipApplication.class, args);
	}


//	@ConfigurationProperties(prefix="spring.datasource")
//	@Bean
//	public DataSource getDataSource() {
//		return DataSourceBuilder
//				.create()
//				.build();
//	}


}
