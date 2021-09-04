package com.example.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:msa-linkage.yml")
public class MsaLinkageConfig {

    @Value("${target-url}")
    private String url;

    @Value("${target-path}")
    private String path;

    @Value("${memory-size}")
    private int size;

}
