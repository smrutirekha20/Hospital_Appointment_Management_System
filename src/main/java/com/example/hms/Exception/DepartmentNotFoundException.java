package com.example.hms.Exception;

public class DepartmentNotFoundException extends RuntimeException {
   private final String message;
   public DepartmentNotFoundException(String message){
     this.message=message;
    }

  @Override
  public String getMessage(){
    return message;
  }
}
