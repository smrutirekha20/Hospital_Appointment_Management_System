package com.example.hms.Service;

import com.example.hms.RequestDto.DoctorRequest;
import com.example.hms.ResponseDto.DoctorResponse;

public interface DoctorService {

    DoctorResponse createDoctorProfile(Integer adminUserId, Integer departmentId, Integer specializationId, DoctorRequest doctorRequest);
    DoctorResponse updateDoctorProfile(Integer adminUserId, Integer doctorId, Integer departmentId, Integer specializationId, DoctorRequest doctorRequest);
}
