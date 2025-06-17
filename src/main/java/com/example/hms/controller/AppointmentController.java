package com.example.hms.controller;

import com.example.hms.RequestDto.AppointmentRequest;
import com.example.hms.ResponseDto.AppointmentResponse;
import com.example.hms.Service.AppointmentService;
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
public class AppointmentController {

    private final AppResponseBuilder appResponseBuilder;
    private final AppointmentService appointmentService;
    @Operation(description = "The end point can be used to book appointment by patient", responses =
            {
                    @ApiResponse(responseCode = "200", description = "appointment booked"),
                    @ApiResponse(responseCode = "404", description = "patientId,doctor name not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PostMapping("/patients/{patientId}/doctors/{doctorName}")
    @PreAuthorize("hasAuthority('PATIENT_WRITE') OR hasAuthority('PATIENT_READ')")
    public ResponseEntity<ResponseStructure<AppointmentResponse>> bookAppointments(@PathVariable Integer patientId,
                                                                                   @PathVariable String doctorName, @RequestBody AppointmentRequest appointmentRequest){
        AppointmentResponse appointmentResponse = appointmentService.bookAppointment(patientId,doctorName,appointmentRequest);
        return appResponseBuilder.success(HttpStatus.CREATED,"Appointment Booked By Patient",appointmentResponse);
    }
    @Operation(description = "The end point can be used to reschedule the pending appointment", responses =
            {
                    @ApiResponse(responseCode = "200", description = "Appointment Rescheduled"),
                    @ApiResponse(responseCode = "404", description = "appointmentId not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PutMapping("/admin/reschedule/appointment/{appointmentId}")
    @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public ResponseEntity<ResponseStructure<AppointmentResponse>> reschedulePendingAppointmentByAdmin(@PathVariable Integer adminId,@PathVariable Integer appointmentId,
                                                                                                     @RequestBody AppointmentRequest request){
        AppointmentResponse appointmentResponse = appointmentService.reschedulePendingAppointmentByAdmin(adminId,appointmentId, request);
        return appResponseBuilder.success(HttpStatus.CREATED,"Rescheduled appointment By Admin",appointmentResponse);
    }
}
