package com.example.hms.RequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SlotRequest {

    @NotNull
    @NotBlank(message = "slot date is required")
    private LocalDate slotDate;

    @NotNull
    @NotBlank(message = "slot time is required")
    private LocalTime slotTime;

}
