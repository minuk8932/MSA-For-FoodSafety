package com.example.util_components.util_string;

import com.example.configuration.MsaLinkageConfig;
import com.example.util_components.interfaces.string.UrlBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MicroServiceConnectUrlBuilder implements UrlBuilder {

    private final MsaLinkageConfig linkageConfig;

    @Override
    public String buildUrlByBuffer() {

        StringBuffer buffer = new StringBuffer();
        buffer.append(FORWARD_SLASH).append(linkageConfig.getPath());

        return buffer.toString();

    }
}
