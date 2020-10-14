package com.application.springbootjpademo.exception;

public class BlogAlreadyExistsException extends Exception {
    private String message;
    public BlogAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
