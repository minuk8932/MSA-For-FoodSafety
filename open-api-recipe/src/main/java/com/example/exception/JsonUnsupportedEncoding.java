package com.example.exception;

public class JsonUnsupportedEncoding extends RuntimeException{

    public JsonUnsupportedEncoding(String msg, Throwable e) {
        super(msg, e);
    }

    public JsonUnsupportedEncoding(String msg) {
        super(msg);
    }

    public JsonUnsupportedEncoding() {super();}

}
