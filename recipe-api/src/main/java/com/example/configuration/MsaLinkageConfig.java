package com.example.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:msa-linkage.yml")
public class MsaLinkageConfig {

    @Value("${url}")
    private String url;

    @Value("${memory-size}")
    private int size;

}
