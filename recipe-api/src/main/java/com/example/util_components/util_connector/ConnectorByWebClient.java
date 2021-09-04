package com.example.util_components.util_connector;

import com.example.model.Recipes;
import com.example.util_components.interfaces.connecting.Connectable;
import com.example.util_components.util_string.MicroServiceConnectUrlBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConnectorByWebClient implements Connectable {

    private final MicroServiceConnectUrlBuilder urlBuilder;

    private final WebClient openApiWebClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplate.class);

    @Override
    public List<Recipes> requestRemoteData() {
        String remoteApiUrl = null;

        try {

            remoteApiUrl = urlBuilder.Urlbuilder();

        }
        catch (Exception exception) {
            LOGGER.error(">>> RemoteApi Connection >> exception >> ", exception);
        }

        String finalRemoteApiUrl = remoteApiUrl;

        return openApiWebClient
                .get()
                .uri(finalRemoteApiUrl)
                .retrieve()
                .bodyToFlux(Recipes.class)
                .collectList()
                .block();

    }
}
