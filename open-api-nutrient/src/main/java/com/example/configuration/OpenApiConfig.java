package com.example.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:openapi.yml")
public class OpenApiConfig {

    @Value("${apiAuthKey}")
    private String key;

    @Value("${foodSafetyUrl}")
    private String url;

    @Value("${nutrientDB}")
    private String nutrientServiceName;

    @Value("${memorySize}")
    private int size;

}
