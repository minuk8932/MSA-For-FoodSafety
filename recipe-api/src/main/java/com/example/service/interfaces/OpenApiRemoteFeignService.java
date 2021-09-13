package com.example.service.interfaces;

import com.example.model.Recipes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "open-api-recipe")
public interface OpenApiRemoteFeignService {

    @RequestMapping("/open-apis/all")
    List<Recipes> getOpenApiDataList();

}
