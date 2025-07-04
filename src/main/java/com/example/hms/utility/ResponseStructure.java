package com.example.hms.utility;
import org.springframework.http.HttpStatus;

public class ResponseStructure<T> {
    private int status;
    private String message;
    private T data;

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public static <T> ResponseStructure<T> create(HttpStatus status,String message,T data){
        ResponseStructure<T> responseStructure=new ResponseStructure<>();
        responseStructure.setStatus(status.value());
        responseStructure.setMessage(message);
        responseStructure.setData(data);

        return responseStructure;
    }
}
