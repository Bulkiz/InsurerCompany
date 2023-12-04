package com.example.insurercompany.exceptions;

public class ObjectNameAlreadyExistsException extends RuntimeException {
    public ObjectNameAlreadyExistsException(String message) {
        super(message);
    }
}

