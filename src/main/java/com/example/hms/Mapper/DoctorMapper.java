package com.example.hms.Mapper;

import com.example.hms.Entity.Doctor;
import com.example.hms.Entity.User;
import com.example.hms.RequestDto.DoctorRequest;
import com.example.hms.ResponseDto.DoctorResponse;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor mapToDoctor(DoctorRequest request){
        Doctor doctor = new Doctor();
        doctor.setDoctorName(request.getDoctorName());
        doctor.setExperienceYears(request.getExperienceYears());
        doctor.setPhoneNumber(request.getPhoneNumber());
        //doctor.setUser(user);

        return doctor;
    }

    public DoctorResponse mapToDoctorResponse(Doctor doctor){
        DoctorResponse response = new DoctorResponse();

        response.setDoctorId(doctor.getDoctorId());
        response.setDoctorName(doctor.getDoctorName());
        response.setPhoneNumber(doctor.getPhoneNumber());
        response.setExperienceYears(doctor.getExperienceYears());
        response.setEmail(doctor.getUser().getEmail());
        response.setUserId(doctor.getUser().getId());
        response.setCreatedBy(doctor.getCreatedBy().getAdminName());

        return response;
    }
}
