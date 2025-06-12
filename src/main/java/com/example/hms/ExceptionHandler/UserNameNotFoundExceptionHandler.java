package com.example.hms.ExceptionHandler;
import com.example.hms.Exception.UserNotFoundException;
import com.example.hms.Exception.UserNotNameFoundException;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class UserNameNotFoundExceptionHandler {

    private AppResponseBuilder appResponseBuilder;
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorStructure<String>> handleUserNotFoundException(UserNotNameFoundException ex){
        return appResponseBuilder.error(HttpStatus.CONFLICT, ex.getMessage(), "User not found");
    }

}
