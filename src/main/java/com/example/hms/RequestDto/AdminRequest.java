package com.example.hms.RequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdminRequest {
    @NotNull
    @NotBlank(message = "admin name is required")
    private String adminName;

    @NotNull
    @NotBlank(message = "admin phone number is required")
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;
}
