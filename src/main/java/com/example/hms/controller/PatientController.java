package com.example.hms.controller;
import com.example.hms.RequestDto.PatientRequest;
import com.example.hms.ResponseDto.PageResponse;
import com.example.hms.ResponseDto.PatientResponse;
import com.example.hms.Service.PatientService;
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
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class PatientController {


    private final PatientService patientService;
    private final AppResponseBuilder appResponseBuilder;
    @Operation(description = "The end point can be used to create Patient", responses =
            {
                    @ApiResponse(responseCode = "200", description = "patient created"),
                    @ApiResponse(responseCode = "404", description = " patient userId not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PostMapping("/patient/{userId}")
    @PreAuthorize("hasAuthority('PATIENT_WRITE')")
    public ResponseEntity<ResponseStructure<PatientResponse>> registerPatient(@PathVariable Integer userId,@RequestBody PatientRequest request) {
        PatientResponse patientResponse = patientService.registerPatient(userId,request);
        return appResponseBuilder.success(HttpStatus.CREATED, "patient registered successfully", patientResponse);
    }
    @Operation(description = "The end point can be used to update the patient profile", responses =
            {
                    @ApiResponse(responseCode = "200", description = "patient updated"),
                    @ApiResponse(responseCode = "404", description = "patient not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })

    @PutMapping("/admin/{adminId}/patient/{patientId}")
   // @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public ResponseEntity<ResponseStructure<PatientResponse>> updatePatientProfile(@PathVariable Integer adminId,@PathVariable Integer patientId,@RequestBody PatientRequest patientRequest) {
        PatientResponse patientResponse = patientService.updatePatientByAdmin(adminId,patientId,patientRequest);
        return appResponseBuilder.success(HttpStatus.CREATED, "Patient details updated", patientResponse);
    }
    @Operation(description = "The end point can be used to get all the patient", responses =
            {
                    @ApiResponse(responseCode = "200", description = "get all patient using pagination"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @GetMapping("admin/patient")
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public ResponseEntity<ResponseStructure<PageResponse<PatientResponse>>> getAllPatients(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        PageResponse<PatientResponse> response = patientService.getAllPatient(page, size);
        return appResponseBuilder.success(HttpStatus.FOUND, "Patient found", response);
    }
}
