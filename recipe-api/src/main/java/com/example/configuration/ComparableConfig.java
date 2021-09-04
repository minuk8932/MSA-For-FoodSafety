package com.example.configuration;

import com.example.model.Recipes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class ComparableConfig implements Comparable<ComparableConfig>{

    private Recipes recipes;

    private double similarity;

    public ComparableConfig(Recipes field, double similarity) {
        this.recipes = field;
        this.similarity = similarity;
    }

    @Override
    public int compareTo(ComparableConfig cc) {
        return this.similarity > cc.similarity ? -1: 1;
    }

}
