package com.example.hms.Service;

import com.example.hms.RequestDto.DoctorRequest;
import com.example.hms.ResponseDto.DoctorResponse;

public interface DoctorService {

    DoctorResponse createDoctorProfile(Integer adminId, Integer departmentId, Integer specializationId, DoctorRequest doctorRequest);
    DoctorResponse updateDoctorProfile(Integer adminId, Integer doctorId, Integer departmentId, Integer specializationId, DoctorRequest doctorRequest);
}
