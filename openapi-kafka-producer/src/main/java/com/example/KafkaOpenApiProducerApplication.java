package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootApplication
public class KafkaOpenApiProducerApplication implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> customKafkaTemplate;

    private final String TOPIC_NAME = "Recipe-OpenApi";

    public static void main(String[] args) {
        SpringApplication.run(KafkaOpenApiProducerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        ListenableFuture<SendResult<String, String>> future = customKafkaTemplate.send(TOPIC_NAME, "data");

        future.addCallback(new KafkaSendCallback<String, String>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
            }
        });

        System.exit(0);
    }
}
