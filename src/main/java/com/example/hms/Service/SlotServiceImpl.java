package com.example.hms.Service;

import com.example.hms.Entity.*;
import com.example.hms.Exception.AdminNotFoundException;
import com.example.hms.Exception.DepartmentNotFoundException;
import com.example.hms.Exception.DoctorNotFoundException;
import com.example.hms.Exception.SpecializationNotFoundException;
import com.example.hms.Mapper.SlotMapper;
import com.example.hms.Repository.*;
import com.example.hms.RequestDto.SlotRequest;
import com.example.hms.ResponseDto.SlotResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SlotServiceImpl implements SlotService{

    private final AdminRepository adminRepository;
    private final DepartmentRepository departmentRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorRepository doctorRepository;
    private final SlotMapper slotMapper;
    private final SlotRepository slotRepository;

    public List<SlotResponse> createSlot(Integer adminUserId, Integer departmentId, Integer specializationId,
                                         Integer doctorUserId, List<SlotRequest> request){
        Admin admin = adminRepository.findById(adminUserId)
                .orElseThrow(()->new AdminNotFoundException("Admin not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        Specialization specialization = specializationRepository.findById(specializationId)
                .orElseThrow(()->new SpecializationNotFoundException("specialization not found"));
        Doctor doctor = doctorRepository.findByUserId(doctorUserId)
                .orElseThrow(()->new DoctorNotFoundException("Doctor not found"));
        if (!doctor.getDepartment().getDepartmentId().equals(departmentId) ||
                !doctor.getSpecialization().getSpecializationId().equals(specializationId)) {
            throw new RuntimeException("Doctor does not belong to provided department/specialization");
        }
        List<Slot> slots = new ArrayList<>();
        for (SlotRequest req : request) {
            Slot slot = slotMapper.mapToSlot(req);
            slot.setDoctor(doctor);
            slot.setCreatedBy(admin);
            slots.add(slot);
        }
        List<Slot> savedSlots = slotRepository.saveAll(slots);
        return savedSlots.stream()
                .map(slotMapper::mapToSlotResponse)
                .toList();
    }

    @Override
    public List<SlotResponse> getAvailableSlots(String doctorName) {
        List<Slot> slots = slotRepository.getAvailableSlots(doctorName);
        return slots.stream().map(slotMapper::mapToSlotResponse)
                .toList();
    }
}
