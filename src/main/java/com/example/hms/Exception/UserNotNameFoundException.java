package com.example.hms.Exception;


public class UserNotNameFoundException extends RuntimeException {
    private final String message;
    public UserNotNameFoundException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}

