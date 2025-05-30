package com.example.hms.ExceptionHandler;

import com.example.hms.Exception.SpecializationNotFoundException;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class SpecializationNotFoundExceptionHandler {

    private final AppResponseBuilder appResponseBuilder;

    @ExceptionHandler(SpecializationNotFoundException.class)
    public ResponseEntity<ErrorStructure<String>> handleEmailExists(SpecializationNotFoundException ex){
        return appResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Specialization Not found");
        }
}
