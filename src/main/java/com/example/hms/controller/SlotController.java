package com.example.hms.controller;

import com.example.hms.RequestDto.SlotRequest;
import com.example.hms.ResponseDto.PageResponse;
import com.example.hms.ResponseDto.PatientResponse;
import com.example.hms.ResponseDto.SlotResponse;
import com.example.hms.Service.SlotService;
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

import java.util.List;

@RestController
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class SlotController {

    private final AppResponseBuilder appResponseBuilder;
    private final SlotService slotService;
    @Operation(description = "The end point can be used to create slot by admin", responses =
            {
                    @ApiResponse(responseCode = "200", description = "slot created"),
                    @ApiResponse(responseCode = "404", description = "adminId not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PostMapping("admin/{adminUserId}/department/{departmentId}/specialization/{specializationId}/doctor/{doctorUserId}/slot")
    public ResponseEntity<ResponseStructure<List<SlotResponse>>> createSlot(@PathVariable Integer adminUserId,@PathVariable Integer departmentId, @PathVariable Integer specializationId,
                                                                      @PathVariable Integer doctorUserId, @RequestBody List<SlotRequest> request){
        List<SlotResponse> slotResponses = slotService.createSlot(adminUserId,departmentId,specializationId,doctorUserId,request);
        return appResponseBuilder.success(HttpStatus.CREATED,"slot created successfully by admin",slotResponses);
    }
    @Operation(description = "The end point can be used to update slot", responses =
            {
                    @ApiResponse(responseCode = "200", description = "Slot updated"),
                    @ApiResponse(responseCode = "404", description = "doctorName not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @GetMapping("/patient/doctor/{doctorName}/slots")
    public ResponseEntity<ResponseStructure<List<SlotResponse>>> getAvailableSlots(@PathVariable String doctorName){
        List<SlotResponse> slotResponses = slotService.getAvailableSlots(doctorName);
        return appResponseBuilder.success(HttpStatus.FOUND,"available slot found",slotResponses);
    }
    @Operation(description = "The end point can be used to get all the slot", responses =
            {
                    @ApiResponse(responseCode = "200", description = "get all slot"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @GetMapping("patient/slot")
    @PreAuthorize("hasAuthority('PATIENT_READ')")
    public ResponseEntity<ResponseStructure<PageResponse<SlotResponse>>> getAllPatients(
            @RequestParam Integer doctorId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        PageResponse<SlotResponse> response = slotService.getAllSlot(doctorId,page, size);
        return appResponseBuilder.success(HttpStatus.OK, "Patient found", response);
    }
}
