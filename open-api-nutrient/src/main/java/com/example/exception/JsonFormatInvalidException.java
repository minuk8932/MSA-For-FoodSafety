package com.example.exception;

public class JsonFormatInvalidException extends RuntimeException {

    public JsonFormatInvalidException(String msg, Throwable e) {
        super(msg, e);
    }

    public JsonFormatInvalidException(String msg) {
        super(msg);
    }

    public JsonFormatInvalidException() {super();}

}
