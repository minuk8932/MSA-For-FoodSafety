package com.example.exception;

public class NoSuchAvailableDataException extends RuntimeException {

    public NoSuchAvailableDataException(String msg, Throwable e) {
        super(msg, e);
    }

    public NoSuchAvailableDataException(String msg) {
        super(msg);
    }

    public NoSuchAvailableDataException() {super();}

}
