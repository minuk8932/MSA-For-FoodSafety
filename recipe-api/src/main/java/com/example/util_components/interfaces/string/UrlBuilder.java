package com.example.util_components.interfaces.string;

import java.util.List;

public interface UrlBuilder {

    String FORWARD_SLASH = "/";
    String COLON = ":";

    int NULL = -1;

    String Urlbuilder(int port, List<String> paths) ;

}
