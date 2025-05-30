package com.example.hms.RequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SpecializationRequest {

    @NotNull
    @NotBlank(message = "specialization is required")
    private String specializationName;

}
