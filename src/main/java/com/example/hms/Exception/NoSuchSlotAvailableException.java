package com.example.hms.Exception;


public class NoSuchSlotAvailableException extends RuntimeException {
    private String message;
    public NoSuchSlotAvailableException(String message) {
      this.message=message;
    }
    public String getMessage(){
      return message;
    }
}
