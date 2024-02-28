package com.codecraftsmanae.kafka.admin.client;

import com.codecraftsmanae.kafka.admin.exception.KafkaClientException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class KafkaAdminClient {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaAdminClient.class);
	@Value("${kafka.topics}")
	private List<String> topicNames;
	@Value("${kafka.partitions.number}")
	private Integer numberOfPartitions;

	@Value("${kafka.replication.factor}")
	private Short replicationFactor;

	@Value("${retry.maxRetries}")
	private Integer maxRetries;
	private final AdminClient adminClient;

	private final RetryTemplate retryTemplate;

	public KafkaAdminClient(AdminClient adminClient, RetryTemplate retryTemplate) {
		this.adminClient = adminClient;
		this.retryTemplate = retryTemplate;
	}

	public void createTopics() {
		CreateTopicsResult createTopicsResult;
		try {
			createTopicsResult = retryTemplate.execute(this::doCreateTopics);
		} catch (Exception e) {
			throw new KafkaClientException("Reached max number of retries to create kafka topic(s)!", e);
		}
		checkTopicsCreated();
	}

	private void checkTopicsCreated() {
		Collection<TopicListing> topics = getTopics();
		int retryCount = 1;


	}

	private CreateTopicsResult doCreateTopics(RetryContext retryContext) {
		LOG.info("creating {} topics, attempt {}", topicNames.size(), retryContext.getRetryCount());
		List<NewTopic> kafkaTopics = topicNames.stream().map(topicName -> new NewTopic(
				topicName.trim(),
						numberOfPartitions,
						replicationFactor))
				.collect(Collectors.toList());
		return adminClient.createTopics(kafkaTopics);
	}


	private Collection<TopicListing> getTopics() {
		Collection<TopicListing> topics;
		try {
			topics = retryTemplate.execute(this::doGetTopics);
		} catch (Exception e) {
			throw new KafkaClientException("Reached max number of retries to read kafka topic(s)!", e);
		}
		return topics;
	}

	private Collection<TopicListing> doGetTopics(RetryContext retryContext) throws ExecutionException, InterruptedException {
		Collection<TopicListing> topicListings = adminClient.listTopics().listings().get();

		if (!CollectionUtils.isEmpty(topicListings)) {
			topicListings.forEach(topic -> LOG.info("Topic with name {}", topic));
		}
		return topicListings;
	}
}
