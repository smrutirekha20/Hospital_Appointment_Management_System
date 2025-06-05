package com.example.hms.Service;

import com.example.hms.RequestDto.LoginRequest;
import com.example.hms.RequestDto.UserRequest;
import com.example.hms.ResponseDto.UserResponse;
import jakarta.validation.Valid;

public interface UserService{
    UserResponse saveUser(UserRequest request);
    UserResponse login(LoginRequest request);
}
