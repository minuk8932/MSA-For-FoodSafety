package com.example.util_components.interfaces.connecting;

import reactor.core.publisher.Mono;

public interface Connectable {

    String STATUS_CODE = "statusCode";
    String HEADER = "header";
    String BODY = "body";

    Mono<String> requestOpenApiData(String key, String name, int start, int end);

}
