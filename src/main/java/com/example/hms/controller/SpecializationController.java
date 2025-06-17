package com.example.hms.controller;

import com.example.hms.RequestDto.SpecializationRequest;
import com.example.hms.ResponseDto.SpecializationResponse;
import com.example.hms.Service.SpecializationService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${hospital.base_url}")
public class SpecializationController {

    private final SpecializationService specializationService;
    private final AppResponseBuilder appResponseBuilder;

    @Operation(description = "The end point can be used to save the data", responses =
            {
                    @ApiResponse(responseCode = "201", description = "Restaurant created"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PostMapping("/admin/{adminId}/{departmentId}/departments/specializations")
     @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public ResponseEntity<ResponseStructure<SpecializationResponse>> createDepartment(@PathVariable Integer adminId, @PathVariable Integer departmentId, @RequestBody SpecializationRequest request) {
       SpecializationResponse specializationResponse = specializationService.createSpecialization( adminId,departmentId,request);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Department created successfully by admin", specializationResponse);
    }

    @Operation(description = "The end point can be used to update specialization", responses =
            {
                    @ApiResponse(responseCode = "200", description = "Specialization updated"),
                    @ApiResponse(responseCode = "404", description = "adminId not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PutMapping("/admin/{adminId}/{departmentId}/departments/{specializationId}/specializations")
    @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public ResponseEntity<ResponseStructure<SpecializationResponse>> updateDepartment(@PathVariable Integer adminId, @PathVariable Integer departmentId,@PathVariable Integer specializationId, @RequestBody SpecializationRequest request) {
        SpecializationResponse specializationResponse = specializationService.updateSpecialization( adminId,departmentId,specializationId,request);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Department updated successfully by admin", specializationResponse);
    }
}
