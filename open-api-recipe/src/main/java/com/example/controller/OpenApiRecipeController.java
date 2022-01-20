package com.example.controller;

import com.example.model.Recipes;
import com.example.service.OpenApiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/open-apis")
public class OpenApiRecipeController {

    private final Logger LOGGER = LoggerFactory.getLogger(OpenApiRecipeController.class);

    private final OpenApiServiceImpl openApiService;
    private final KafkaTemplate<String, Recipes> kafkaTemplate;

//    @GetMapping(path = "/all")
//    public ResponseEntity<List<Recipes>> getAllRecipes() {
//        List<Recipes> recipesList = (List<Recipes>) openApiService.getListAll();
//
//        return new ResponseEntity<>(recipesList, HttpStatus.OK);
//    }

    @GetMapping(path = "/all")
    public ResponseEntity<String> getAllRecipes() {
        List<Recipes> recipesList = (List<Recipes>) openApiService.getListAll();

        Map<String, String> props = new HashMap<>();
        props.put("value.serializer", "com.example.kafka.serializer.RecipeSerializer");

        final String[] response = {""};

        recipesList.stream().iterator().forEachRemaining(recipes ->
                kafkaTemplate.send("open-api topic", recipes).addCallback(
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

        return new ResponseEntity<>(response[0], HttpStatus.OK);
    }

}
