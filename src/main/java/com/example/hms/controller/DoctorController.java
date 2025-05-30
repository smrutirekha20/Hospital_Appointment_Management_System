package com.example.hms.controller;


import com.example.hms.RequestDto.DoctorRequest;

import com.example.hms.ResponseDto.DoctorResponse;
import com.example.hms.Service.DoctorService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final AppResponseBuilder appResponseBuilder;

    @PostMapping("/admin/{adminUserId}/department/{departmentId}/specialization/{specializationId}/doctor")
    public ResponseEntity<ResponseStructure<DoctorResponse>> createDoctorProfile(@PathVariable Integer adminUserId, @PathVariable Integer departmentId, @PathVariable Integer specializationId, @RequestBody DoctorRequest doctorRequest) {
        DoctorResponse doctorResponse = doctorService.createDoctorProfile( adminUserId,departmentId,specializationId,doctorRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Doctor created successfully by admin", doctorResponse);
    }

    @PutMapping("/admin/{adminUserId}/doctor/{doctorId}/department/{departmentId}/specialization/{specializationId}")
    public ResponseEntity<ResponseStructure<DoctorResponse>> updateDoctorProfile(
            @PathVariable Integer adminUserId,
            @PathVariable Integer doctorId,
            @PathVariable Integer departmentId,
            @PathVariable Integer specializationId,
            @RequestBody DoctorRequest doctorRequest){
        DoctorResponse response = doctorService.updateDoctorProfile(
                adminUserId, doctorId, departmentId, specializationId, doctorRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED,
                "Doctor updated successfully by admin", response);
    }
}

