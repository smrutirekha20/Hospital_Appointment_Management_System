package com.example.hms.Service;

import com.example.hms.RequestDto.SpecializationRequest;
import com.example.hms.ResponseDto.SpecializationResponse;

public interface SpecializationService {

    SpecializationResponse createSpecialization(Integer adminUserId, Integer departmentId, SpecializationRequest request);
    SpecializationResponse updateSpecialization(Integer adminUserId, Integer departmentId, Integer specializationId, SpecializationRequest request);
}
