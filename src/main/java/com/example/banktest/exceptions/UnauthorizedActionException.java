package com.example.banktest.exceptions;

public class UnauthorizedActionException extends Exception {
    public UnauthorizedActionException(String message) {
        super(message);
    }
}
