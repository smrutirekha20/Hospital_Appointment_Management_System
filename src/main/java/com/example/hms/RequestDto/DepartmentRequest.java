package com.example.hms.RequestDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentRequest {

    @NotNull
    @NotBlank(message = "department name is required")
    private String departmentName;
}
