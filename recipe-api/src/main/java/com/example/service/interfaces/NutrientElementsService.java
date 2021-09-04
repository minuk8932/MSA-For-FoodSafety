package com.example.service.interfaces;

import com.example.service.interfaces.categorize.Categorizable;

import java.util.Map;

public interface NutrientElementsService extends Categorizable {

    int CAPACITY = 10;

    double[] ingestedNutrientsAvg(Map<String, Integer> ate);

}
