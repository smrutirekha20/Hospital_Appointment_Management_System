package com.example.hms.controller;


import com.example.hms.RequestDto.DoctorRequest;

import com.example.hms.ResponseDto.DoctorResponse;
import com.example.hms.Service.DoctorService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ErrorStructure;
import com.example.hms.utility.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(description = "The end point can be used to create doctor profile by admin", responses =
            {
                    @ApiResponse(responseCode = "200", description = "doctor created"),
                    @ApiResponse(responseCode = "404", description = "admin not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PostMapping("/admin/{adminId}/department/{departmentId}/specialization/{specializationId}/doctor")
    public ResponseEntity<ResponseStructure<DoctorResponse>> createDoctorProfile(@PathVariable Integer adminId, @PathVariable Integer departmentId, @PathVariable Integer specializationId, @RequestBody DoctorRequest doctorRequest) {
        DoctorResponse doctorResponse = doctorService.createDoctorProfile( adminId,departmentId,specializationId,doctorRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Doctor created successfully by admin", doctorResponse);
    }
    @Operation(description = "The end point can be used to update the doctor profile", responses =
            {
                    @ApiResponse(responseCode = "200", description = "doctor profile updated"),
                    @ApiResponse(responseCode = "404", description = "adminId not found"),
                    @ApiResponse(responseCode = "404", description = "doctorId not found"),
                    @ApiResponse(responseCode = "404", description = "departmentId not found"),
                    @ApiResponse(responseCode = "404", description = "specializationId not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })

    @PutMapping("/admin/{adminId}/doctor/{doctorId}/department/{departmentId}/specialization/{specializationId}")
    public ResponseEntity<ResponseStructure<DoctorResponse>> updateDoctorProfile(
            @PathVariable Integer adminId,
            @PathVariable Integer doctorId,
            @PathVariable Integer departmentId,
            @PathVariable Integer specializationId,
            @RequestBody DoctorRequest doctorRequest){
        DoctorResponse response = doctorService.updateDoctorProfile(
                adminId, doctorId, departmentId, specializationId, doctorRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED,
                "Doctor updated successfully by admin", response);
    }
}

