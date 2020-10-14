package com.application.springbootjpademo.exception;

public class BlogNotFoundException extends Exception {

    private String message;
    public BlogNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
