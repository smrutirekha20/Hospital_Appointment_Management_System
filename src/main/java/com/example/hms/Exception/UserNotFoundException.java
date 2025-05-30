package com.example.hms.Exception;


public class UserNotFoundException extends RuntimeException {
    private final String message;
    public UserNotFoundException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}

