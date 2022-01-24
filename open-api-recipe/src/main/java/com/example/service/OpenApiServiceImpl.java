package com.example.service;

import com.example.model.Recipes;
import com.example.repository.OpenApiRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenApiServiceImpl implements OpenApiService {

    private final Logger LOGGER = LoggerFactory.getLogger(OpenApiServiceImpl.class);
    private final String RECIPE_TOPIC = "recipe-openapi";

    private final OpenApiRepository openApiRepository;
    private final KafkaTemplate<String, Recipes> kafkaTemplate;

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
    public void produceAll(List<?> list) {      // for kafka
        if(list.size() == 0) return;

        final String[] response = {""};

        list
                .stream()
                .forEach(recipes ->
                        kafkaTemplate.send(RECIPE_TOPIC, (Recipes) recipes).addCallback(
                                new ListenableFutureCallback<SendResult<String, Recipes>>() {
                                    @Override
                                    public void onFailure(Throwable ex) {
                                        LOGGER.error(ex.getMessage(), ex);
                                    }

                                    @Override
                                    public void onSuccess(SendResult<String, Recipes> result) {
                                        response[0] = result.toString();
                                        LOGGER.info(result.toString());
                                    }
                                })
                );

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
