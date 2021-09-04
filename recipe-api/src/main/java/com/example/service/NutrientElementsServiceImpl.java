package com.example.service;

import com.example.model.data_enums.NutrientElements;
import com.example.service.interfaces.NutrientElementsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NutrientElementsServiceImpl implements NutrientElementsService {

    @Override
    public Map<String, NutrientElements> configurationSettingsCategorize() {
        Map<String, NutrientElements> categories = new HashMap<>();

        for(NutrientElements nutrientElement:
                Stream
                        .of(NutrientElements.values())
                        .collect(Collectors.toList())) {

            categories.put(NUT_FORMATS[nutrientElement.getSequence()], nutrientElement);
        }

        return categories;
    }

    @Override
    public List<?> ingestedNutrientsCategoryArranger(NutrientElements nElements) {
        return Stream.builder()
                .add(nElements.getTotal()).add(nElements.getKcal())
                .add(nElements.getCarbohydrate()).add(nElements.getProtein()).add(nElements.getFat())
                .add(nElements.getSugar()).add(nElements.getSodium()).add(nElements.getCholesterol())
                .add(nElements.getSaturatedFattyAcid()).add(nElements.getTransFat())
                .build()
                .collect(Collectors.toList());
    }

    @Override
    public double[] ingestedNutrientsAvg(Map<String, Integer> ate) {
        Map<String, NutrientElements> categories = configurationSettingsCategorize();
        double size = ate.size();

        double[] ingestedTotal = new double[CAPACITY];

        for(Map.Entry<String, Integer> entry: ate.entrySet()) {
            String category = entry.getKey();
            int amount = entry.getValue();
            if(!categories.containsKey(category)) return null;

            List<Double> nutrientList = ingestedNutrientsCategoryArranger(categories.get(category))
                    .stream()
                    .map(i -> new Double((Double) i))
                    .collect(Collectors.toList());

            int index = 0;
            for(double ing: nutrientList) {
                ingestedTotal[index++] += ing * amount;
            }
        }

        return Arrays
                .stream(ingestedTotal)
                .map(i -> i / size)
                .toArray();
    }
}
