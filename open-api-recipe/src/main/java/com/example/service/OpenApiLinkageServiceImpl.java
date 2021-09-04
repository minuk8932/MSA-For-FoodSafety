package com.example.service;

import com.example.configuration.OpenApiConfig;
import com.example.model.ManualPairs;
import com.example.model.Recipes;
import com.example.util_components.util_connector.OpenApiConnectorByWebClient;
import com.example.util_components.util_string.Casting;
import com.example.util_components.util_string.parse.LastIndexTracker;
import com.example.util_components.util_string.parse.OpenApiJsonDataParser;
import com.example.util_components.util_string.parse.PairTagBuilder;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.UnknownContentTypeException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenApiLinkageServiceImpl implements OpenApiLinkageService {

    private final OpenApiConfig openApiConfig;
    private final OpenApiServiceImpl openApiService;

    private final OpenApiJsonDataParser openApiJsonDataParser;
    private final OpenApiConnectorByWebClient byWebClient;
    private final Casting casting;
    private final LastIndexTracker indexTracker;
    private final PairTagBuilder pairTagBuilder;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplate.class);

    @Override
    public void updateByOpenApiData() {
        int start = 1;
        int end = INTERVAL;

        final int SIZE = indexTracker.findTag(openApiConfig.getKey(), openApiConfig.getRecipeServiceName());
        List<String> responses = new ArrayList<>();

        while(start <= SIZE) {
            end = Math.min(end, SIZE);

            try {

                responses.add(byWebClient
                        .requestOpenApiData(openApiConfig.getKey(), openApiConfig.getRecipeServiceName(), start,  end));

            }
            catch (UnknownContentTypeException unknownContentTypeException) {
                LOGGER.error(">>> Open API >> End of pages >> ", unknownContentTypeException);
                break;
            }

            start += INTERVAL;
            end += INTERVAL;
        }

        for(String response: responses) {

            JSONArray jsonRecipe = openApiJsonDataParser
                    .jsonDataParser(openApiConfig.getRecipeServiceName(), response);

            if(jsonRecipe == null) break;
            List<Recipes> apiDataList = new ArrayList<>();

            jsonRecipe = (JSONArray) jsonRecipe.get(0);

            for(Object json: jsonRecipe) {
                JSONObject recipe = (JSONObject) json;

                Recipes apiData = (Recipes) jsonToModel(recipe);

                if (openApiService.isContainsField(apiData)) {
                    continue;
                }

                apiDataList.add(apiData);
            }

            openApiService.saveAll(apiDataList);
        }
    }

    @Override
    public Object jsonToModel(JSONObject object) {
        List<Object> values = new ArrayList<>();

        for(final String FORMAT: RECIPE_JSON_FORMATS){
            values.add(casting.valueValidator(object.get(FORMAT)));
        }

        List<ManualPairs> pairsList = pairTagBuilder.pairListBuilder(object);

        return Recipes.builder()
                .id(casting.toLong(values.get(0)))
                .recipeName(casting.toString(values.get(1))).category(casting.toString(values.get(2)))
                .cookingMaterialExample(casting.toString(values.get(3))).cookingCompletionExample(casting.toString(values.get(4)))
                .ingredients(casting.toString(values.get(5))).cookingMethod(casting.toString(values.get(6)))
                .kcal(casting.toDouble(values.get(7)))
                .carbohydrate(casting.toDouble(values.get(8))).protein(casting.toDouble(values.get(9))).fat(casting.toDouble(values.get(10)))
                .sodium(casting.toDouble(values.get(11)))
                .manualPairsList(pairsList)
                .build();
    }
}
