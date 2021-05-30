package com.intercorp.clientsdeathprediction.adapter.database.exception;

import com.intercorp.clientsdeathprediction.config.exception.GenericException;

public class DatabaseException extends GenericException {
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
