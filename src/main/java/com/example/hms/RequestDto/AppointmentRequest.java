package com.example.hms.RequestDto;

import com.example.hms.Enum.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AppointmentRequest {

//    @NotNull
//    @NotBlank(message = "doctor name is required")
//    private String doctorName;
//
    @NotBlank(message = "date is required")
    private LocalDate slotDate;

    @NotBlank(message = "time is required")
    private LocalTime slotTime;

    @NotBlank(message = "appointment date and time required")
    private LocalDateTime appointmentDateAndTime;

    @NotBlank(message = "appointment status is required")
    private AppointmentStatus appointmentStatus;
}
