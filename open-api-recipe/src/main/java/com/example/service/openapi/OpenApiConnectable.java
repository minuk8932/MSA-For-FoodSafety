package com.example.service.openapi;

public interface OpenApiConnectable extends JsonDataAccessible {

    int INTERVAL = 1_000;

    void updateByOpenApiData();

}
