package com.example.hms.controller;


import com.example.hms.RequestDto.DoctorRequest;

import com.example.hms.ResponseDto.DoctorResponse;
import com.example.hms.Service.DoctorService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final AppResponseBuilder appResponseBuilder;

    @PostMapping("/admin/department/{departmentId}/specialization/{specializationId}/doctor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseStructure<DoctorResponse>> createDoctorProfile( @PathVariable Integer departmentId, @PathVariable Integer specializationId, @RequestBody DoctorRequest doctorRequest) {
        DoctorResponse doctorResponse = doctorService.createDoctorProfile(departmentId,specializationId,doctorRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Doctor created successfully by admin", doctorResponse);
    }

    @PutMapping("/admin/doctor/{doctorId}/department/{departmentId}/specialization/{specializationId}")
    public ResponseEntity<ResponseStructure<DoctorResponse>> updateDoctorProfile(
            @PathVariable Integer doctorId,
            @PathVariable Integer departmentId,
            @PathVariable Integer specializationId,
            @RequestBody DoctorRequest doctorRequest){
        DoctorResponse response = doctorService.updateDoctorProfile(
                doctorId, departmentId, specializationId, doctorRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED,
                "Doctor updated successfully by admin", response);
    }
}

