package com.example.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:openapi.yml")
public class OpenApiConfig {

    @Value("${api-auth-key}")
    private String key;

    @Value("${food-safety-url}")
    private String url;

    @Value("${recipe-db}")
    private String recipeServiceName;

    @Value("${memory-size}")
    private int size;

}
