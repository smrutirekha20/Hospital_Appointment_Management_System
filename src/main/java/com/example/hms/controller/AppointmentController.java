package com.example.hms.controller;

import com.example.hms.RequestDto.AppointmentRequest;
import com.example.hms.ResponseDto.AppointmentResponse;
import com.example.hms.Service.AppointmentService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class AppointmentController {

    private final AppResponseBuilder appResponseBuilder;
    private final AppointmentService appointmentService;

    @PostMapping("/patients/{patientId}/doctors/{doctorName}")
    public ResponseEntity<ResponseStructure<AppointmentResponse>> bookAppointments(@PathVariable Integer patientId,
                                                                                   @PathVariable String doctorName, @RequestBody AppointmentRequest appointmentRequest){
        AppointmentResponse appointmentResponse = appointmentService.bookAppointment(patientId,doctorName,appointmentRequest);
        return appResponseBuilder.success(HttpStatus.CREATED,"Appointment Booked By Patient",appointmentResponse);
    }
    @PutMapping("/admin/reschedule/appointment/{appointmentId}")
    public ResponseEntity<ResponseStructure<AppointmentResponse>> reschedulePendingAppointmentByAdmin(@PathVariable Integer appointmentId,
                                                                                                     @RequestBody AppointmentRequest request){
        AppointmentResponse appointmentResponse = appointmentService.reschedulePendingAppointmentByAdmin(appointmentId, request);
        return appResponseBuilder.success(HttpStatus.CREATED,"Rescheduled appointment By Admin",appointmentResponse);
    }
}
