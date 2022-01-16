package com.example.util_components.util_string.parse;

import com.example.util_components.util_connector.OpenApiConnectorByWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LastIndexTracker {

    private final OpenApiConnectorByWebClient byWebClient;

    private final OpenApiJsonDataParser jsonDataParser;

    public int findTag(String key, String name) {

        String jsonText = byWebClient
                .requestOpenApiData(key, name, 1, 2);

        return jsonDataParser
                .onlyTakeIndex(name, jsonText);

    }

}
