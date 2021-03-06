package com.example;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaClient
@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class OpenApiRecipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiRecipeApplication.class);
    }

}
