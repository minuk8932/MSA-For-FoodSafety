package com.example.service.interfaces;

import com.example.model.Recipes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface OpenApiRemoteService {

    Mono<List<Recipes>> getOpenApiDataList();

}
