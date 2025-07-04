package com.example.hms.Service;

import com.example.hms.RequestDto.SlotRequest;
import com.example.hms.ResponseDto.SlotResponse;

import java.util.List;

public interface SlotService {

   List<SlotResponse> createSlot(Integer adminUserId, Integer doctorUserId, Integer departmentId, Integer specializationId, List<SlotRequest> request);
   List<SlotResponse> getAvailableSlots(String doctorName);
}
