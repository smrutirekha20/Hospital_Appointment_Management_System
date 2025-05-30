package com.example.hms.ResponseDto;

import com.example.hms.Enum.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private UserRole userRole;
    private String email;

}
