package com.learning.post.exception;

public class EmailAlreadyPresentException extends RuntimeException {
    String resourceName;

    public EmailAlreadyPresentException(String resourceName) {
        super(String.format("%s Alerady exists ", resourceName));
        this.resourceName = resourceName;

    }
}
