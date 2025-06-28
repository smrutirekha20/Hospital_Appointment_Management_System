package com.example.hms.ResponseDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientResponse {

    private Integer patientId;
    private String patientName;
    private String email;
    private String age;
    private String gender;
    private String phoneNumber;
   // private String updatedBy;
}
