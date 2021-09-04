package com.example.service.interfaces.recommend;

import com.example.model.Recipes;

import java.util.List;

public interface Recommendable {

    List<?> menuRecommender(List<Recipes> fields, double[] ingested);

}
