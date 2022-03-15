package com.example.util_components.util_string.parse;

import com.example.util_components.util_connector.OpenApiConnectorByWebClient;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LastIndexTracker {

    private final OpenApiConnectorByWebClient byWebClient;

    private final OpenApiJsonDataParser jsonDataParser;

    public int findTag(String key, String name) {

        Mono<String> jsonText = byWebClient
                .requestOpenApiData(key, name, 1, 2);

        AtomicInteger index = new AtomicInteger();
        jsonText.subscribe(text ->
            index.set(jsonDataParser.onlyTakeIndex(name, text))
        );

        return index.get();
    }

}
