package com.example.hms.RequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorRequest {
    @NotNull
    @NotBlank(message = "user email and password is required")
    private UserRequest user;

    @NotNull
    @NotBlank(message = "doctor name is required")
    private String doctorName;

    @NotNull
    @NotBlank(message = "experience year should have to mention")
    private double experienceYears;

    @NotNull
    @NotBlank(message = "phone number is required")
    private String phoneNumber;
}
