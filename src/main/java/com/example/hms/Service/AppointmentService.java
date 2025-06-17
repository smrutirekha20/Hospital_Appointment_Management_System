package com.example.hms.Service;

import com.example.hms.RequestDto.AppointmentRequest;
import com.example.hms.ResponseDto.AppointmentResponse;

public interface AppointmentService {
    AppointmentResponse bookAppointment(Integer patientUserId, String doctorName, AppointmentRequest request);
    AppointmentResponse reschedulePendingAppointmentByAdmin(Integer adminId,Integer appointmentId,AppointmentRequest request);
}
