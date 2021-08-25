package com.example.util_components.util_string;

import com.example.util_components.interfaces.string.UrlBuilder;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class OpenApiUrlBuilder implements UrlBuilder {
    @Override
    public String openApiUrlBuilder(String key, String name, int startIdx, int endIdx)
            throws UnsupportedEncodingException {

        StringBuilder urlBuilder = new StringBuilder();

        urlBuilder.append(FORWARD_SLASH).append(URLEncoder.encode(key, ENCODING_TYPE));
        urlBuilder.append(FORWARD_SLASH).append(URLEncoder.encode(name, ENCODING_TYPE));
        urlBuilder.append(FORWARD_SLASH).append(URLEncoder.encode(FORMAT_TYPE, ENCODING_TYPE));
        urlBuilder.append(FORWARD_SLASH).append(startIdx);
        urlBuilder.append(FORWARD_SLASH).append(endIdx);

        return urlBuilder.toString();

    }
}
