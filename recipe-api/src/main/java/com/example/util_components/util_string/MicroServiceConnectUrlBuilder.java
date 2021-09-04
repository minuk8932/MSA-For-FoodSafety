package com.example.util_components.util_string;

import com.example.util_components.interfaces.string.UrlBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MicroServiceConnectUrlBuilder implements UrlBuilder {

    @Override
    public String Urlbuilder(int port, List<String> paths) {

        StringBuffer buffer = new StringBuffer();
        if(port != NULL) buffer.append(port).append(COLON);

        for(String path: paths) {
            buffer.append(FORWARD_SLASH).append(path);
        }

        return buffer.toString();

    }
}
