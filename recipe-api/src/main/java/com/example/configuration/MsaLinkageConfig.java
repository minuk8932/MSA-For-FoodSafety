package com.example.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@PropertySource("classpath:msa-linkage.yml")
public class MsaLinkageConfig {

    @Value("${target-url}")
    private String url;

    @Value("${target-port}")
    private int port;

    @Value("${paths}")
    private List<String> paths;

    @Value("${memory-size}")
    private int size;

}
