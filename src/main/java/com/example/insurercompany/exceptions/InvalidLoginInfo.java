package com.example.insurercompany.exceptions;

public class InvalidLoginInfo extends RuntimeException {
    public InvalidLoginInfo(String message) {
        super(message);
    }
}