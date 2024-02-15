package com.EventFlowHub.datasource.to.kafka.service;

import com.EventFlowHub.datasource.to.kafka.service.config.DatasourceToKafkaServiceConfigData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DatasourceToKafkaServiceApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DatasourceToKafkaServiceApplication.class);

	private final DatasourceToKafkaServiceConfigData datasourceToKafkaServiceConfigData;

	//pros and cons of constructor injection vs using @Autowired (filed injection)
	//field injection doesn't allow the object to be final => no Immutability
	// (Immutability is possible with constructor injection and is great for robust and threadsafe apps)
	//constructor injection doesn't allow reflection => better performance
	public DatasourceToKafkaServiceApplication(DatasourceToKafkaServiceConfigData datasourceToKafkaServiceConfigData) {
		this.datasourceToKafkaServiceConfigData = datasourceToKafkaServiceConfigData;
	}

	public static void main(String[] args) {
		SpringApplication.run(DatasourceToKafkaServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info(datasourceToKafkaServiceConfigData.getWelcomeMessage());
		LOG.info(Arrays.toString(datasourceToKafkaServiceConfigData.getDatasourceKeywords().toArray(new String[] {})));
	}
}
