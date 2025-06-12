package com.example.hms.ResponseDto;

import com.example.hms.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private UserRole userRole;
    private String email;
    private String token;
}
