package com.example.hms.controller;

import com.example.hms.RequestDto.SlotRequest;
import com.example.hms.ResponseDto.PageResponse;
import com.example.hms.ResponseDto.SlotResponse;
import com.example.hms.Service.SlotService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class SlotController {

    private final AppResponseBuilder appResponseBuilder;
    private final SlotService slotService;

    @PostMapping("admin/department/{departmentId}/specialization/{specializationId}/doctor/{doctorUserId}/slot")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseStructure<List<SlotResponse>>> createSlot(@PathVariable Integer departmentId, @PathVariable Integer specializationId,
                                                                      @PathVariable Integer doctorUserId, @RequestBody List<SlotRequest> request){
        List<SlotResponse> slotResponses = slotService.createSlot(departmentId,specializationId,doctorUserId,request);
        return appResponseBuilder.success(HttpStatus.CREATED,"slot created successfully by admin",slotResponses);
    }

    @GetMapping("/patient/doctor/{doctorName}/slots")
    public ResponseEntity<ResponseStructure<List<SlotResponse>>> getAvailableSlotsByDoctorName(@PathVariable String doctorName){
        List<SlotResponse> slotResponses = slotService.getAvailableSlotsByDoctorName(doctorName);
        return appResponseBuilder.success(HttpStatus.FOUND,"available slot found",slotResponses);
    }

    @GetMapping("/patient")
    public ResponseEntity<ResponseStructure<PageResponse<SlotResponse>>> getAvailableSlots(@RequestParam Integer doctorId,
                                                                                           @RequestParam(defaultValue = "0") Integer pageNumber,
                                                                                           @RequestParam(defaultValue = "10") Integer pageSize){
        PageResponse<SlotResponse> slotResponses = slotService.getAvailableSlots(doctorId,pageNumber,pageSize);
        return appResponseBuilder.success(HttpStatus.FOUND,"available slots found with page size and page number",slotResponses);
    }
}
