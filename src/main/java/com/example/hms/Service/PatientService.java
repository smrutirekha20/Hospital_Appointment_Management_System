package com.example.hms.Service;

import com.example.hms.RequestDto.PatientRequest;
import com.example.hms.ResponseDto.PageResponse;
import com.example.hms.ResponseDto.PatientResponse;

import java.util.List;

public interface PatientService {
    PatientResponse registerPatient(Integer userId,PatientRequest patientRequest);
    PatientResponse updatePatientByAdmin(Integer adminId,Integer patientId,PatientRequest patientRequest);
    PageResponse<PatientResponse> getAllPatient(Integer page, Integer size);
    List<PatientResponse> searchPatientByPatientName(String subString);
}
