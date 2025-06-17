package com.example.hms.Service;

import com.example.hms.RequestDto.SpecializationRequest;
import com.example.hms.ResponseDto.SpecializationResponse;

public interface SpecializationService {

    SpecializationResponse createSpecialization(Integer adminId, Integer departmentId, SpecializationRequest request);
    SpecializationResponse updateSpecialization(Integer adminId, Integer departmentId, Integer specializationId, SpecializationRequest request);
}
