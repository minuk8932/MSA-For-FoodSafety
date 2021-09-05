package com.example.service;

import com.example.model.Recipes;
import com.example.service.interfaces.OpenApiRemoteService;
import com.example.util_components.util_string.MicroServiceConnectUrlBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OpenApiRemoteServiceImpl implements OpenApiRemoteService {

    private final MicroServiceConnectUrlBuilder urlBuilder;

    private final WebClient msaLinkageWebClient;

    @Override
    @HystrixCommand
    public List<?> getOpenApiDataList() {

        return msaLinkageWebClient
                .get()
                .uri(urlBuilder.buildUrlByBuffer())
                .retrieve()
                .bodyToFlux(Recipes.class)
                .collectList()
                .block();
    }
}
