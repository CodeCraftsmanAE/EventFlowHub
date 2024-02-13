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

	public DatasourceToKafkaServiceApplication(
			DatasourceToKafkaServiceConfigData datasourceToKafkaServiceConfigData) {
		this.datasourceToKafkaServiceConfigData = datasourceToKafkaServiceConfigData;
	}
	public static void main(String[] args) {
		SpringApplication.run(DatasourceToKafkaServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello");
		LOG.info(Arrays.toString(datasourceToKafkaServiceConfigData.getDatasourceKeywords().toArray(new String[] {})));
	}
}
