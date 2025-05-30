package com.example.hms.Mapper;

import com.example.hms.Entity.Patient;
import com.example.hms.RequestDto.PatientRequest;
import com.example.hms.ResponseDto.PatientResponse;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient mapToPatient(PatientRequest request){

        Patient patient = new Patient();

        patient.setPatientName(request.getPatientName());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhoneNumber(request.getPhoneNumber());

        return patient;
    }

    public PatientResponse mapToPatientResponse(Patient patient){
        PatientResponse patientResponse = new PatientResponse();

        patientResponse.setPatientId(patient.getPatientId());
        patientResponse.setPatientName(patient.getPatientName());
        patientResponse.setAge(patient.getAge());
        patientResponse.setGender(patient.getGender());
        patientResponse.setEmail(patient.getUser().getEmail());
        patientResponse.setPhoneNumber(patient.getPhoneNumber());
      //  patientResponse.setUpdatedBy(patient.getUpdatedBy().getAdminName());

        return patientResponse;
    }
}
