package com.example.hms.Mapper;

import com.example.hms.Entity.User;
import com.example.hms.RequestDto.UserRequest;
import com.example.hms.ResponseDto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(UserRequest userRequest){
        User user = new User();
        user.setUserRole(userRequest.getUserRole());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }
    public UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserRole(user.getUserRole());
        userResponse.setEmail(user.getEmail());

        return userResponse;

    }
}
