package com.example.hms.controller;


import com.example.hms.RequestDto.LoginRequest;
import com.example.hms.RequestDto.UserRequest;
import com.example.hms.ResponseDto.UserResponse;
import com.example.hms.Service.UserService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ResponseStructure;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class UserController {

    private final AppResponseBuilder appResponseBuilder;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody @Valid UserRequest
                                                                           userRequest) {
        UserResponse userResponse = userService.saveUser(userRequest);
        return appResponseBuilder.success(HttpStatus.CREATED, "User created", userResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<UserResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {
        UserResponse userResponse = userService.login(loginRequest);


        return appResponseBuilder.success(HttpStatus.OK, "logged in", userResponse);
    }
}
