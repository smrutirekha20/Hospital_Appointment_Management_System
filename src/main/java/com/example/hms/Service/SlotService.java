package com.example.hms.Service;

import com.example.hms.RequestDto.SlotRequest;
import com.example.hms.ResponseDto.PageResponse;
import com.example.hms.ResponseDto.SlotResponse;

import java.util.List;

public interface SlotService {

   List<SlotResponse> createSlot( Integer doctorUserId, Integer departmentId, Integer specializationId, List<SlotRequest> request);
   List<SlotResponse> getAvailableSlotsByDoctorName(String doctorName);
   PageResponse<SlotResponse> getAvailableSlots(Integer doctorId,Integer pageNumber,Integer pageSize);
}
