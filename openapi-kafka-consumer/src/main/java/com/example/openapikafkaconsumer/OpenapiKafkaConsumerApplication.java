package com.example.openapikafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;

@SpringBootApplication
public class OpenapiKafkaConsumerApplication {

	public static final Logger LOGGER = LoggerFactory.getLogger(OpenapiKafkaConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OpenapiKafkaConsumerApplication.class, args);
	}

	@KafkaListener(topics = "Recipe-OpenApi", groupId = "recipe-group-00")
	public void recordListener(ConsumerRecord<String, String> record) {
		LOGGER.info(record.toString());
	}

	@KafkaListener(topics = "Recipe-OpenApi", groupId = "recipe-group-01")
	public void singleTopicListener(String messageValue) {
		LOGGER.info(messageValue);
	}

	@KafkaListener(topics = "Recipe-OpenApi", groupId = "recipe-group-02", properties = {
			"max.poll.interval.ms:60000", "auto.offset.reset:earliest"
	})
	public void singleTopicWithPropertiesListener(String messageValue) {
		LOGGER.info(messageValue);
	}

	@KafkaListener(topics = "Recipe-OpenApi", groupId = "recipe-group-03", concurrency = "3")
	public void concurrentTopicListener(String messageValue) {
		LOGGER.info(messageValue);
	}

	@KafkaListener(topicPartitions = {
		@TopicPartition(topic = "test01", partitions = {"0", "1"}),
			@TopicPartition(topic = "test02",
					partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "3"))
	}, groupId = "recipe-group-04")
	public void listenSpecificPartition(ConsumerRecord<String, String> record) {
		LOGGER.info(record.toString());
	}
}
