package com.example.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class OpenApiServiceImpl implements OpenApiService {

    @Override
    public void updateByOpenApiData() throws ExecutionException, InterruptedException {

    }

    @Override
    public void saveAll(List<?> list) {

    }

    @Override
    public boolean isContainsField(Object object) {
        return false;
    }

    @Override
    public Object jsonToModel(JSONObject object) {
        return null;
    }
}