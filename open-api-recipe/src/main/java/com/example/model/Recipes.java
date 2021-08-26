package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter
@Builder
@Table(name = "recipes", schema = "food_safety_msa")
public class Recipes {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "category")
    private String category;

    @Column(name = "cooking_method")
    private String cookingMethod;

    @Column(name = "cooking_material_example")
    private String cookingMaterialExample;

    @Column(name = "cooking_completion_example")
    private String cookingCompletionExample;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "kcal")
    private double kcal;

    @Column(name = "carbohydrate")
    private double carbohydrate;

    @Column(name = "protein")
    private double protein;

    @Column(name = "fat")
    private double fat;

    @Column(name = "sodium")
    private double sodium;

    @OneToMany(cascade = CascadeType.ALL)
    @Builder.Default
    @JoinColumn(name = "recipe_id")
    private final List<ManualPairs> manualPairsList = new ArrayList<>();

}
