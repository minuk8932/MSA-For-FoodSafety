package com.example.util_components.util_string.parse;

import com.example.exception.JsonFormatInvalidException;
import com.example.util_components.interfaces.string.parsing.JsonParsable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OpenApiJsonDataParser implements JsonParsable {
    @Override
    public JSONArray jsonDataParser(String name, String jsonText) {

        JSONParser parser = new JSONParser();

        return Stream.of(parser)
                .map(p -> {
                    try {
                        return (JSONObject) p.parse(jsonText);
                    } catch (Exception exception) {
                        throw new JsonFormatInvalidException("잘못된 json 데이터 형식 입니다.");
                    }
                })
                .map(json ->
                        (JSONObject) json.get(name))
                .map(data ->
                        (JSONArray) data.get(LIST_FLAG))
                .collect(Collectors.toCollection(JSONArray::new));

    }

    @Override
    public int onlyTakeIndex(String name, String jsonText) {

        JSONParser parser = new JSONParser();

        return Stream.of(parser)
                .map(p -> {
                    try {
                        return (JSONObject) p.parse(jsonText);
                    } catch (Exception exception) {
                        throw new JsonFormatInvalidException("잘못된 json 데이터 형식 입니다.");
                    }
                })
                .map(json ->
                        (JSONObject) json.get(name))
                .map(data ->
                        data.get(TOTAL).toString())
                .mapToInt(Integer::parseInt)
                .max()
                .getAsInt();

    }
}
