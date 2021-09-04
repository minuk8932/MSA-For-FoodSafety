package com.example.service;

import com.example.model.Recipes;
import com.example.repository.OpenApiRepository;
import com.example.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenApiServiceImpl implements OpenApiService {

    private final OpenApiRepository openApiRepository;

    @Override
    public void saveAll(List<?> list) {
        if(list.size() == 0) return;

        openApiRepository.saveAll(Arrays.asList(
                list.stream()
                        .toArray(Recipes[]::new)
                        .clone()
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isContainsField(Object object) {
        Recipes nutrient = (Recipes) object;
        long id = nutrient.getId();

        return openApiRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<?> getListAll() {
        return openApiRepository.findAll();
    }

}
