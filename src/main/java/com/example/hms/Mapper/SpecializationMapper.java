package com.example.hms.Mapper;

import com.example.hms.Entity.Specialization;
import com.example.hms.RequestDto.SpecializationRequest;
import com.example.hms.ResponseDto.SpecializationResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SpecializationMapper {

    public Specialization mapToSpecialization(SpecializationRequest specializationRequest){
        Specialization specialization = new Specialization();
        specialization.setSpecializationName(specializationRequest.getSpecializationName());

        return specialization;
    }

    public SpecializationResponse mapToSpecializationResponse(Specialization specialization){

        SpecializationResponse specializationResponse = new SpecializationResponse();
        specializationResponse.setSpecializationId(specialization.getSpecializationId());
        specializationResponse.setSpecializationName(specialization.getSpecializationName());
        specializationResponse.setCreatedBy(specialization.getCreatedBy().getUser().getEmail());
       // specializationResponse.setCreatedBy(specialization.getCreatedBy().getUser().getId());

        return specializationResponse;
    }
}
