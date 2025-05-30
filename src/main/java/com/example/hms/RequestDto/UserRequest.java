package com.example.hms.RequestDto;

import com.example.hms.Enum.UserRole;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequest {
    @NotNull(message = "Role is required")
    private UserRole  userRole;

    @NotNull(message = "email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "email must be end with @gmail.com")
    private String email;

    @NotNull
    @NotBlank(message = "password is required")
    private String password;

}
