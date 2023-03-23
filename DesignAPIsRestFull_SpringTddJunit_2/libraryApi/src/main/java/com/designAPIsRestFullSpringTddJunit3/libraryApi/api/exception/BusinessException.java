package com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String s) {
        super(s);
    }
}
