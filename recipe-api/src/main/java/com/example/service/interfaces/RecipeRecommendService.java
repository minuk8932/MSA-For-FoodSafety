package com.example.service.interfaces;

import com.example.model.Recipes;
import com.example.service.interfaces.recommend.Recommendable;

import java.util.List;

public interface RecipeRecommendService extends Recommendable {

    int PACK = 10;
    int NUTRIENT_TYPES = 10;

    List<Object> categorize(Recipes recipe);

}
