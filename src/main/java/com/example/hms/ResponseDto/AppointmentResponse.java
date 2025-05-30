package com.example.hms.ResponseDto;

import com.example.hms.Enum.AppointmentStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AppointmentResponse {

    private Integer appointmentId;
    private String doctorName;
    private LocalDate slotDate;
    private LocalTime slotTime;
    private LocalDateTime appointmentDateAndTime;
    private AppointmentStatus appointmentStatus;
}
