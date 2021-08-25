package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter
@Builder
@Table(name = "nutrients", schema = "food_safety_msa")
public class Nutrients {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nutrient_name")
    private String nutrientName;

    @Column(name = "category")
    private String category;

    @Column(name = "total")
    private double total;

    @Column(name = "kcal")
    private double kcal;

    @Column(name = "carbohydrate")
    private double carbohydrate;

    @Column(name = "protein")
    private double protein;

    @Column(name = "fat")
    private double fat;

    @Column(name = "sugar")
    private double sugar;

    @Column(name = "sodium")
    private double sodium;

    @Column(name = "cholesterol")
    private double cholesterol;

    @Column(name = "saturated_fatty_acid")
    private double saturatedFattyAcid;

    @Column(name = "trans_fat")
    private double transFat;

}
