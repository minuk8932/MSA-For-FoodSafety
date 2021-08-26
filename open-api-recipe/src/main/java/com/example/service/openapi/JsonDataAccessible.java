package com.example.service.openapi;

import org.json.simple.JSONObject;

public interface JsonDataAccessible {

    String[] RECIPE_JSON_FORMATS = {"RCP_SEQ", "RCP_NM", "RCP_PAT2"
            , "ATT_FILE_NO_MK", "ATT_FILE_NO_MAIN", "RCP_PARTS_DTLS", "RCP_WAY2"
            , "INFO_ENG", "INFO_CAR", "INFO_PRO", "INFO_FAT", "INFO_NA"};

    Object jsonToModel(JSONObject object);

}
