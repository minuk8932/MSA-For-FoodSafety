package com.example.service.openapi;

import org.json.simple.JSONObject;

public interface JsonDataAccessible {

    String[] FOOD_JSON_FORMATS = {"NUM", "DESC_KOR", "GROUP_NAME", "SERVING_SIZE",
            "NUTR_CONT1", "NUTR_CONT2", "NUTR_CONT3", "NUTR_CONT4",
            "NUTR_CONT5", "NUTR_CONT6", "NUTR_CONT7", "NUTR_CONT8",
            "NUTR_CONT9"};

    Object jsonToModel(JSONObject object);

}
