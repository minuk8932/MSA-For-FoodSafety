package com.example.controller;

import com.example.model.Recipes;
import com.example.service.NutrientElementsServiceImpl;
import com.example.service.OpenApiRemoteServiceImpl;
import com.example.service.RecipeRecommendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("recipes")
public class RecipeController {

    private final RecipeRecommendServiceImpl recipeRecommendService;

    private final NutrientElementsServiceImpl nutrientElementsService;

    private final OpenApiRemoteServiceImpl openApiRemoteService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplate.class);

    @GetMapping("/recommend")
    public ResponseEntity<List<Recipes>> recommendMenuByIngestedFood(@RequestBody Map<String, Integer> ate) {
        List<Recipes> recommend = null;

        try {

            List<Recipes> remoteDataList = (List<Recipes>) openApiRemoteService.getOpenApiDataList();
            double[] ingested = nutrientElementsService.ingestedNutrientsAvg(ate);

            recommend = (List<Recipes>) recipeRecommendService.menuRecommender(remoteDataList, ingested);

        } catch (NullPointerException nullPointerException) {
            LOGGER.error(">>> Menu recommend >> exception >> ", nullPointerException, " >> wrong recipe list !!");
            nullPointerException.printStackTrace();
        }

        return new ResponseEntity<>(recommend, HttpStatus.OK);
    }

}
