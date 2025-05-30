package com.example.hms.Exception;

public class DoctorNotFoundException extends RuntimeException {
   private final String message;
   public DoctorNotFoundException(String message){
     this.message=message;
    }

  @Override
  public String getMessage(){
    return message;
  }
}
