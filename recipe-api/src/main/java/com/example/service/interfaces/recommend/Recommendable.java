package com.example.service.interfaces.recommend;

import com.example.model.Recipes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface Recommendable {

    Mono<Recipes> menuRecommender(Mono<List<Recipes>> fields, double[] ingested);

}
