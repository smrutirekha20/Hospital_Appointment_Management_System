package com.example.hms.RequestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
   // @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "email must be end with @gmail.com")
    private String email;

    @NotNull(message = "password can not be null")
    // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$"
    // ,message = "at least one uppercase one lowercase,one digit one special character and contain minimum 8 character")
    private String password;


    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
}

