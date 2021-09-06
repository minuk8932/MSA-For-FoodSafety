package com.example.configuration.web_client;

import com.example.configuration.MsaLinkageConfig;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final MsaLinkageConfig linkageConfig;

    @LoadBalanced
    @Bean
    public WebClient msaLinkageWebClient() throws URISyntaxException {

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(
                        configurator ->
                                configurator
                                        .defaultCodecs()
                                        .maxInMemorySize(linkageConfig.getSize())
                )
                .build();

        URI redirectURL = new URI(linkageConfig.getRedirect());

        return WebClient.builder()
                .baseUrl(linkageConfig.getUrl())
                .filter((request, next) -> {

                    ClientRequest filtered = ClientRequest
                                .from(request)
                                .url(redirectURL)
                                .build();

                    return next.exchange(filtered);
                })
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(exchangeStrategies)
                .build();
    }

}
