package com.example.hms.ResponseDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SlotResponse {

    private Integer slotId;
    private LocalDate slotDate;
    private LocalTime slotTime;
    private String departmentName;
    private String specializationName;
    private String doctorName;
    private String createdBy;

}
