package com.example.hms.Service;

import com.example.hms.Entity.*;
import com.example.hms.Exception.DepartmentNotFoundException;
import com.example.hms.Exception.DoctorNotFoundException;
import com.example.hms.Exception.SpecializationNotFoundException;
import com.example.hms.Mapper.SlotMapper;
import com.example.hms.Repository.*;
import com.example.hms.RequestDto.SlotRequest;
import com.example.hms.ResponseDto.PageResponse;
import com.example.hms.ResponseDto.SlotResponse;
import com.example.hms.Security.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SlotServiceImpl implements SlotService{

    private final AdminRepository adminRepository;
    private final DepartmentRepository departmentRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorRepository doctorRepository;
    private final SlotMapper slotMapper;
    private final SlotRepository slotRepository;
    private final AuthUtil authUtil;

    public List<SlotResponse> createSlot( Integer departmentId, Integer specializationId,
                                         Integer doctorUserId, List<SlotRequest> request){
//        Admin admin = adminRepository.findById(adminUserId)
//                .orElseThrow(()->new AdminNotFoundException("Admin not found"));

        User user = authUtil.getCurrentUser();
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
            slot.setCreatedBy(user.getAdmin());
            slots.add(slot);
        }
        List<Slot> savedSlots = slotRepository.saveAll(slots);
        return savedSlots.stream()
                .map(slotMapper::mapToSlotResponse)
                .toList();
    }

    @Override
    public List<SlotResponse> getAvailableSlotsByDoctorName(String doctorName) {
        List<Slot> slots = slotRepository.getAvailableSlots(doctorName);
        return slots.stream().map(slotMapper::mapToSlotResponse)
                .toList();
    }
    public PageResponse<SlotResponse> getAvailableSlots(Integer doctorId,Integer pageNumber,Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Slot> slotPage = slotRepository.findAll(pageable);

        List<SlotResponse> slotResponses = slotPage.getContent().stream()
                .map(slotMapper::mapToSlotResponse)
                .toList();

        return new PageResponse<SlotResponse>(
                slotResponses,
                slotPage.getNumber(),
                slotPage.getSize(),
                slotPage.getTotalElements(),
                slotPage.getTotalPages(),
                slotPage.isLast()
        );
    }

}
