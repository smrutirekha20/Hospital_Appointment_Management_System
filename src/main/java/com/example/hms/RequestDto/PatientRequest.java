package com.example.hms.RequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientRequest {
    @NotNull
    @NotBlank(message = "patient name is required")
    private String patientName;

    @NotNull
    @NotBlank(message = "age is required ")
    private String age;

    @NotNull
    @NotBlank(message = "gender is required")
    private String gender;

    @NotNull
    @NotBlank(message = "phone number is required")
    private String phoneNumber;

}
