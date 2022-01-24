package com.example.service;

import com.example.configuration.ComparableConfig;
import com.example.model.Recipes;
import com.example.service.interfaces.RecipeRecommendService;
import com.example.util_components.util_math.CosineSimilarity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecipeRecommendServiceImpl implements RecipeRecommendService {

    private final CosineSimilarity cosineSimilarity;

    @Override
    public Mono<Recipes> menuRecommender(Mono<List<Recipes>> fields, double[] ingested) {

        return fields.map(field -> {

            PriorityQueue<ComparableConfig> similar = new PriorityQueue<>(
                    Comparator.comparing(ComparableConfig::getSimilarity));

            for (Recipes recipe: field) {

                double[] contains = new double[NUTRIENT_TYPES];
                List<Object> nutrientList = categorize(recipe);

                int index = 0;
                for(Object ing: nutrientList) {
                    contains[index++] = Double.parseDouble(ing.toString());
                }

                double scalar = cosineSimilarity.scalarProduct(ingested, contains);
                double vector = cosineSimilarity.vectorProduct(ingested, contains);

                if(scalar == 0) continue;
                similar.offer(new ComparableConfig(recipe, cosineSimilarity.division(vector, scalar)));

            }

            return similar;

        })
        .map(similar ->
                Objects.requireNonNull(similar.poll()).getRecipes());

    }

    @Override
    public List<Object> categorize(Recipes recipe) {
        return Stream.builder()
                .add(recipe.getKcal())
                .add(recipe.getCarbohydrate()).add(recipe.getProtein()).add(recipe.getFat())
                .add(recipe.getSodium())
                .build()
                .collect(Collectors.toList());
    }

}
