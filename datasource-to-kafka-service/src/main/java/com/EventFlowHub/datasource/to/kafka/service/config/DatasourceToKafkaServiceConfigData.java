package com.EventFlowHub.datasource.to.kafka.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "datasource-to-kafka-service")
public class DatasourceToKafkaServiceConfigData {
	private List<String> datasourceKeywords;
}
