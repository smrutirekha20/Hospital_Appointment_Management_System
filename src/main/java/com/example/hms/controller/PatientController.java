package com.example.hms.controller;
import com.example.hms.RequestDto.PatientRequest;
import com.example.hms.ResponseDto.PageResponse;
import com.example.hms.ResponseDto.PatientResponse;
import com.example.hms.Service.PatientService;
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
public class PatientController {


    private final PatientService patientService;
    private final AppResponseBuilder appResponseBuilder;

    @PostMapping("/patient/{userId}")
   // @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ResponseStructure<PatientResponse>> registerPatient(@PathVariable Integer userId,@RequestBody PatientRequest request) {
        PatientResponse patientResponse = patientService.registerPatient(userId,request);
        return appResponseBuilder.success(HttpStatus.CREATED, "patient registered successfully", patientResponse);
    }

    @PutMapping("/admin/{adminId}/patient/{patientId}")
    @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public ResponseEntity<ResponseStructure<PatientResponse>> updatePatientProfile(@PathVariable Integer adminId,@PathVariable Integer patientId,@RequestBody PatientRequest patientRequest) {
        PatientResponse patientResponse = patientService.updatePatientByAdmin(adminId,patientId,patientRequest);
        return appResponseBuilder.success(HttpStatus.CREATED, "Patient details updated", patientResponse);
    }
    @GetMapping("admin/patient")
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public ResponseEntity<ResponseStructure<PageResponse<PatientResponse>>> getAllPatients(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        PageResponse<PatientResponse> response = patientService.getAllPatient(page, size);
        return appResponseBuilder.success(HttpStatus.FOUND, "Patient found", response);
    }
}
