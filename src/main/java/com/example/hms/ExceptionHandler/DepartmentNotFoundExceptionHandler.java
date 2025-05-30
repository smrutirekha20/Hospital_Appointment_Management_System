package com.example.hms.ExceptionHandler;

import com.example.hms.Exception.DepartmentNotFoundException;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class DepartmentNotFoundExceptionHandler {

    private final AppResponseBuilder appResponseBuilder;

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorStructure<String>> handleEmailExists(DepartmentNotFoundException ex){
        return appResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Department not found");
        }
}
