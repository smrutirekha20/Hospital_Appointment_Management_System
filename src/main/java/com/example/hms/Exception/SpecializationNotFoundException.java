package com.example.hms.Exception;

public class SpecializationNotFoundException extends RuntimeException {
   private final String message;
   public SpecializationNotFoundException(String message){
     this.message=message;
    }

  @Override
  public String getMessage(){
    return message;
  }
}
