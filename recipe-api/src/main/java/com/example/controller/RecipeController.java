package com.example.controller;

import com.example.exception.OpenApiDataIsNullException;
import com.example.model.Recipes;
import com.example.service.NutrientElementsServiceImpl;
import com.example.service.OpenApiRemoteServiceImpl;
import com.example.service.RecipeRecommendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("recipes")
public class RecipeController {

    private final RecipeRecommendServiceImpl recipeRecommendService;

    private final NutrientElementsServiceImpl nutrientElementsService;

    private final OpenApiRemoteServiceImpl openApiRemoteService;

    @GetMapping("/recommend")
    public ResponseEntity<List<Recipes>> recommendMenuByIngestedFood(@RequestBody Map<String, Integer> ate) {

        double[] ingested = nutrientElementsService.ingestedNutrientsAvg(ate);
        List<Recipes> remoteDataList = openApiRemoteService.getOpenApiDataList();

        List<Recipes> recommend = (List<Recipes>) recipeRecommendService.menuRecommender(remoteDataList, ingested);
        return new ResponseEntity<>(recommend, HttpStatus.OK);
    }

}
