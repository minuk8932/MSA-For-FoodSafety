package com.example.configuration;

import com.example.model.data_enums.NutrientElements;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "nut")
@Getter
@RequiredArgsConstructor
public class NutrientsConfig {

    private NutrientElements chicken;
    private NutrientElements pig;
    private NutrientElements cow;
    private NutrientElements fish;
    private NutrientElements rawFish;
    private NutrientElements ramen;
    private NutrientElements coffee;
    private NutrientElements drink;
    private NutrientElements bread;
    private NutrientElements pizza;
    private NutrientElements kimchi;
    private NutrientElements soup;
    private NutrientElements rice;
    private NutrientElements redRiceCake;
    private NutrientElements sundae;
    private NutrientElements fried;

}
