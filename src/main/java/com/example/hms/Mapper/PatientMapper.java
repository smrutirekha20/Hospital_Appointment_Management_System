package com.example.hms.Mapper;

import com.example.hms.Entity.Patient;
import com.example.hms.RequestDto.PatientRequest;
import com.example.hms.ResponseDto.PatientResponse;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class PatientMapper {

    public Patient mapToPatient(PatientRequest request){

//        Patient patient = new Patient();
//
//        patient.setPatientName(request.getPatientName());
//        patient.setAge(request.getAge());
//        patient.setGender(request.getGender());
//        patient.setPhoneNumber(request.getPhoneNumber());
//
//        return patient;
       return Patient.builder()
               .patientName(request.getPatientName())
               .age(request.getAge())
               .gender(request.getGender())
               .phoneNumber(request.getPhoneNumber())
               .build();
    }

    public PatientResponse mapToPatientResponse(Patient patient){
//        PatientResponse patientResponse = new PatientResponse();
//
//        patientResponse.setPatientId(patient.getPatientId());
//        patientResponse.setPatientName(patient.getPatientName());
//        patientResponse.setAge(patient.getAge());
//        patientResponse.setGender(patient.getGender());
//        patientResponse.setEmail(patient.getUser().getEmail());
//        patientResponse.setPhoneNumber(patient.getPhoneNumber());
//      //  patientResponse.setUpdatedBy(patient.getUpdatedBy().getAdminName());
//
        return PatientResponse.builder()
                .patientId(patient.getPatientId())
                .patientName(patient.getPatientName())
                .age(patient.getAge())
                .email(patient.getUser().getEmail())
                .phoneNumber(patient.getPhoneNumber())
                .build();
    }
}
