package com.example.hms.ResponseDto;

import lombok.Data;

@Data
public class DoctorResponse {

    private Integer doctorId;
    private String doctorName;
    private double experienceYears;
    private String phoneNumber;
    private Integer userId;
    private String email;
    private String createdBy;
}
