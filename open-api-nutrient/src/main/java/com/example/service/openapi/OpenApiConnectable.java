package com.example.service.openapi;

import java.util.concurrent.ExecutionException;

public interface OpenApiConnectable extends JsonDataAccessible {

    int INTERVAL = 1_000;

    void updateByOpenApiData() throws ExecutionException, InterruptedException;

}
