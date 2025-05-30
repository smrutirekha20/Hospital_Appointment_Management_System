package com.example.hms.Service;

import com.example.hms.RequestDto.PatientRequest;
import com.example.hms.ResponseDto.PatientResponse;

public interface PatientService {
    PatientResponse registerPatient(Integer userId,PatientRequest patientRequest);
    PatientResponse updatePatientByAdmin(Integer adminUserId,Integer patientUserId,PatientRequest patientRequest);
}
