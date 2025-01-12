package com.spring.hard.exceptions;

public class UnauthorizedProfileException extends RuntimeException {
    public UnauthorizedProfileException(String message) {
        super(message);
    }
}