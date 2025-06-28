package com.example.hms.utility;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseStructure<T> {
    private int status;
    private String message;
    private T data;

    public static <T> ResponseStructure<T> create(HttpStatus status,String message,T data){
        ResponseStructure<T> responseStructure=new ResponseStructure<>();
        responseStructure.setStatus(status.value());
        responseStructure.setMessage(message);
        responseStructure.setData(data);

        return responseStructure;
    }
}
