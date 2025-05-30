package com.example.hms.Exception;

public class ResourceAlreadyExistException extends RuntimeException{

    private  String message;
    public ResourceAlreadyExistException(String message) {
        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}

