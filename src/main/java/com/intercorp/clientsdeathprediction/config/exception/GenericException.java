package com.intercorp.clientsdeathprediction.config.exception;

public abstract class GenericException extends RuntimeException {

    private static final String SPACE = " ";
    private static final String COMMA = ",";

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
