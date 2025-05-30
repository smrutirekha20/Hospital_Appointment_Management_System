package com.example.hms.controller;

import com.example.hms.RequestDto.SpecializationRequest;
import com.example.hms.ResponseDto.SpecializationResponse;
import com.example.hms.Service.SpecializationService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${hospital.base_url}")
public class SpecializationController {

    private final SpecializationService specializationService;
    private final AppResponseBuilder appResponseBuilder;

    @PostMapping("/admin/{adminUserId}/{departmentId}/departments/specializations")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseStructure<SpecializationResponse>> createDepartment(@PathVariable Integer adminUserId, @PathVariable Integer departmentId, @RequestBody SpecializationRequest request) {
       SpecializationResponse specializationResponse = specializationService.createSpecialization( adminUserId,departmentId,request);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Department created successfully by admin", specializationResponse);
    }

    @PutMapping("/admin/{adminUserId}/{departmentId}/departments/{specializationId}/specializations")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseStructure<SpecializationResponse>> createDepartment(@PathVariable Integer adminUserId, @PathVariable Integer departmentId,@PathVariable Integer specializationId, @RequestBody SpecializationRequest request) {
        SpecializationResponse specializationResponse = specializationService.updateSpecialization( adminUserId,departmentId,specializationId,request);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Department updated successfully by admin", specializationResponse);
    }
}
