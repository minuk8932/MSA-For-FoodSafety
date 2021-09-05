package com.example.exception;

public class OpenApiDataIsNullException extends RuntimeException{

    public OpenApiDataIsNullException(String msg, Throwable e) {
        super(msg, e);
    }

    public OpenApiDataIsNullException(String msg) {
        super(msg);
    }

    public OpenApiDataIsNullException() {super();}

}
