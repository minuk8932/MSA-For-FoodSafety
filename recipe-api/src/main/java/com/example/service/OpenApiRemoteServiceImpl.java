package com.example.service;

import com.example.model.Recipes;
import com.example.service.interfaces.OpenApiRemoteService;
import com.example.util_components.util_connector.ConnectorByWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenApiRemoteServiceImpl implements OpenApiRemoteService {

    private final ConnectorByWebClient byWebClient;

    @Override
    public List<?> getOpenApiDataList() {
        List<Recipes> recipeList = byWebClient.requestRemoteData();
        return recipeList;
    }
}
