package com.example.hms.controller;

import com.example.hms.RequestDto.SlotRequest;
import com.example.hms.ResponseDto.SlotResponse;
import com.example.hms.Service.SlotService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class SlotController {

    private final AppResponseBuilder appResponseBuilder;
    private final SlotService slotService;

    @PostMapping("admin/{adminUserId}/department/{departmentId}/specialization/{specializationId}/doctor/{doctorUserId}/slot")
    public ResponseEntity<ResponseStructure<List<SlotResponse>>> createSlot(@PathVariable Integer adminUserId,@PathVariable Integer departmentId, @PathVariable Integer specializationId,
                                                                      @PathVariable Integer doctorUserId, @RequestBody List<SlotRequest> request){
        List<SlotResponse> slotResponses = slotService.createSlot(adminUserId,departmentId,specializationId,doctorUserId,request);
        return appResponseBuilder.success(HttpStatus.CREATED,"slot created successfully by admin",slotResponses);
    }

    @GetMapping("/patient/doctor/{doctorName}/slots")
    public ResponseEntity<ResponseStructure<List<SlotResponse>>> getAvailableSlots(@PathVariable String doctorName){
        List<SlotResponse> slotResponses = slotService.getAvailableSlots(doctorName);
        return appResponseBuilder.success(HttpStatus.FOUND,"available slot found",slotResponses);
    }
}
