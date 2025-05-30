package com.example.hms.utility;

import org.springframework.http.HttpStatus;

public class ErrorStructure<T> {
    private Integer status;
    private String message;
    private T rootCause;
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getStatus() {
        return status;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setRootCause(T rootCause) {
        this.rootCause = rootCause;
    }
    public T getRootCause() {
        return rootCause;
    }
    public static <T> ErrorStructure<T> create(HttpStatus status,String message,T rootCause){
        ErrorStructure<T> errorStructure=new ErrorStructure<>();
        errorStructure.setStatus(status.value());
        errorStructure.setMessage(message);
        errorStructure.setRootCause(rootCause);

        return errorStructure;
    }

}
