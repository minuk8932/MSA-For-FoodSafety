package com.example.service;

import com.example.configuration.ComparableConfig;
import com.example.model.Recipes;
import com.example.service.interfaces.RecipeRecommendService;
import com.example.util_components.util_math.CosineSimilarity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecipeRecommendServiceImpl implements RecipeRecommendService {

    private final CosineSimilarity cosineSimilarity;

    @Override
    public List<?> menuRecommender(List<Recipes> fields, double[] ingested) {

        PriorityQueue<ComparableConfig> similar = new PriorityQueue<>();

        for (Recipes field: fields) {

            double[] contains = new double[NUTRIENT_TYPES];
            List<Object> nutrientList = categorize(field);

            int index = 0;
            for(Object ing: nutrientList) {
                contains[index++] += Double.parseDouble(ing.toString());
            }

            double scalar = cosineSimilarity.scalarProduct(ingested, contains);
            double vector = cosineSimilarity.vectorProduct(ingested, contains);

            similar.offer(new ComparableConfig(field, cosineSimilarity.division(vector, scalar)));

        }

        int size = Math.min(PACK, similar.size());
        return Arrays
                .stream(similar.toArray())
                .map(i -> similar.poll().getRecipes())
                .limit(size)
                .collect(Collectors.toList());

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
