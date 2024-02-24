package com.codecraftsmanae.datasource.to.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.codecraftsmanae")
public class DatasourceToKafkaServiceApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DatasourceToKafkaServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DatasourceToKafkaServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {

		LOG.info("welcome!");
	}
}
