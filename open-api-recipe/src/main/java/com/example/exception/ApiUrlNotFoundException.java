package com.example.exception;

public class ApiUrlNotFoundException extends RuntimeException {

    public ApiUrlNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }

    public ApiUrlNotFoundException(String msg) {
        super(msg);
    }

    public ApiUrlNotFoundException() {super();}

}
