package com.example.controller;

import com.example.model.Recipes;
import com.example.service.openapi.OpenApiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/open-apis")
public class OpenApiRecipeController {

    private final OpenApiServiceImpl openApiService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Recipes>> getAllRecipes() {
        List<Recipes> recipesList = (List<Recipes>) openApiService.getListAll();

        return new ResponseEntity<>(recipesList, HttpStatus.OK);
    }

}
