package com.example.configuration.web_client;

import com.example.configuration.MsaLinkageConfig;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final MsaLinkageConfig linkageConfig;

    @Bean
    public WebClient msaLinkageWebClient() {

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(
                        configurator ->
                                configurator
                                        .defaultCodecs()
                                        .maxInMemorySize(linkageConfig.getSize())
                )
                .build();

        return WebClient.builder().baseUrl(linkageConfig.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(exchangeStrategies)
                .build();
    }

}
