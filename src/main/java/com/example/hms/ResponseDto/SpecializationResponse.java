package com.example.hms.ResponseDto;

import com.example.hms.Entity.Admin;
import lombok.Data;

@Data
public class SpecializationResponse {

    private Integer specializationId;
    private String specializationName;
    private String createdBy;

}
