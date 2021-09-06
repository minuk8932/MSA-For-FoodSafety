package com.example.service;

import com.example.exception.OpenApiDataIsNullException;
import com.example.model.Recipes;
import com.example.service.interfaces.OpenApiRemoteService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OpenApiRemoteServiceImpl implements OpenApiRemoteService {

    private final WebClient webClient;

    private final Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);

    @Override
    @HystrixCommand(commandKey = "recipeInfo", fallbackMethod = "getOpenApiDataListFallback")
    public List<Recipes> getOpenApiDataList() {

        List<Recipes> openApiDataList = webClient
                .get()
                .retrieve()
                .bodyToFlux(Recipes.class)
                .collectList()
                .block();

        return openApiDataList;

    }

    public List<Recipes> getOpenApiDataListFallback(Throwable throwable) {
        logger.error("Exception >> Linkage open-API >> ", throwable);
        throw new OpenApiDataIsNullException("Open API 데이터를 가져올 수 없습니다.");
    }
}
