package com.example.util_components.util_connector;

import com.example.exception.JsonUnsupportedEncoding;
import com.example.util_components.interfaces.connecting.Connectable;
import com.example.util_components.util_string.OpenApiUrlBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class OpenApiConnectorByWebClient implements Connectable {

    private final OpenApiUrlBuilder urlBuilder;

    private final WebClient openApiWebClient;

    @Override
    public Mono<String> requestOpenApiData(String key, String name, int start, int end) {

        String openApiUrl;

        try {

            openApiUrl = urlBuilder.openApiUrlBuilder(key, name, start, end);

        }
        catch (Exception exception) {
            throw new JsonUnsupportedEncoding("지원되지 않는 Encoding 형식입니다.");
        }

        return openApiWebClient
                .get()
                .uri(openApiUrl)
                .retrieve()
                .bodyToMono(String.class);

    }

}
