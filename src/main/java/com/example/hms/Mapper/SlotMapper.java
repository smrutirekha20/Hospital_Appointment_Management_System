package com.example.hms.Mapper;

import com.example.hms.Entity.Slot;
import com.example.hms.RequestDto.SlotRequest;
import com.example.hms.ResponseDto.SlotResponse;
import org.springframework.stereotype.Component;

@Component
public class SlotMapper {

    public Slot mapToSlot(SlotRequest slotRequest){
        Slot slot = new Slot();
        slot.setSlotDate(slotRequest.getSlotDate());
        slot.setSlotTime(slotRequest.getSlotTime());

        return slot;
    }

    public SlotResponse mapToSlotResponse(Slot slot){
        SlotResponse slotResponse = new SlotResponse();

        slotResponse.setSlotId(slot.getSlotId());
        slotResponse.setSlotDate(slot.getSlotDate());
        slotResponse.setSlotTime(slot.getSlotTime());
        slotResponse.setDepartmentName(slot.getDoctor().getDepartment().getDepartmentName());
        slotResponse.setSpecializationName(slot.getDoctor().getSpecialization().getSpecializationName());
        slotResponse.setDoctorName(slot.getDoctor().getDoctorName());
        slotResponse.setCreatedBy(slot.getCreatedBy().getAdminName());

        return slotResponse;
    }
}
