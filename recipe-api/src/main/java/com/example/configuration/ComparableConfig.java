package com.example.configuration;

import com.example.model.Recipes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class ComparableConfig {

    private Recipes recipes;

    private double similarity;

    public ComparableConfig(Recipes recipes, double similarity) {
        this.recipes = recipes;
        this.similarity = similarity;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public double getSimilarity() {
        return 1 - similarity;
    }

}
