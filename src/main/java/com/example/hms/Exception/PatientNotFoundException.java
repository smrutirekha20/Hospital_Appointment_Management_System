package com.example.hms.Exception;

public class PatientNotFoundException extends RuntimeException {
  private  String message;
    public PatientNotFoundException(String message) {
        this.message=message;
    }
    @Override
    public String getMessage(){
      return message;
    }
}
