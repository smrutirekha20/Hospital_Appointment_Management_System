package com.example.hms.ExceptionHandler;

import com.example.hms.Exception.AppointmentNotFoundException;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class AppointmentNotFoundExceptionHandler {

    private final AppResponseBuilder appResponseBuilder;

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ErrorStructure<String>> handleEmailExists(AppointmentNotFoundException ex){
        return appResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Appointment not found.");
        }
}
