package com.example.hms.Service;

import com.example.hms.Entity.*;
import com.example.hms.Exception.AdminNotFoundException;
import com.example.hms.Exception.DepartmentNotFoundException;
import com.example.hms.Exception.DoctorNotFoundException;
import com.example.hms.Exception.SpecializationNotFoundException;
import com.example.hms.Mapper.SlotMapper;
import com.example.hms.Repository.*;
import com.example.hms.RequestDto.SlotRequest;
import com.example.hms.ResponseDto.PageResponse;
import com.example.hms.ResponseDto.PatientResponse;
import com.example.hms.ResponseDto.SlotResponse;
import com.example.hms.Security.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SlotServiceImpl implements SlotService {

    private final AdminRepository adminRepository;
    private final DepartmentRepository departmentRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorRepository doctorRepository;
    private final SlotMapper slotMapper;
    private final SlotRepository slotRepository;
    private final AuthUtil authUtil;

    public List<SlotResponse> createSlot(Integer adminUserId, Integer departmentId, Integer specializationId,
                                         Integer doctorUserId, List<SlotRequest> request) {
        Admin admin = adminRepository.findById(adminUserId)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found"));

        User currentUser = authUtil.getCurrentUser();

        if (!admin.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to update this admin");
        }
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        Specialization specialization = specializationRepository.findById(specializationId)
                .orElseThrow(() -> new SpecializationNotFoundException("specialization not found"));
        Doctor doctor = doctorRepository.findByUserId(doctorUserId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
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

    public PageResponse<SlotResponse> getAllSlot(Integer doctorId, Integer page, Integer size) {
        int defaultPageSize = 10;
        int pageSize = (size == null || size <= 0) ? defaultPageSize : size;
        int pageNumber = (page == null || page <= 0) ? 1 : page;

        int offset = (pageNumber - 1) * pageSize;

        List<Slot> slots = slotRepository.findSlotsByDoctorIdWithPagination(doctorId, pageSize, offset);
        long total = slotRepository.countByDoctorId(doctorId);

        List<SlotResponse> responses = slots.stream()
                .map(slotMapper::mapToSlotResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                responses,
                pageNumber,
                pageSize,
                total,
                (int) Math.ceil((double) total / pageSize),
                pageNumber * pageSize >= total
        );
    }
    public List<SlotResponse> filterAvailableSlot(String slotDate,Integer doctorId){

        if (slotDate != null && slotDate.isBlank()) {
            slotDate = null;
        }

        List<Slot> slots = slotRepository.findAvailableSlotsByPartialDate(slotDate, doctorId);
        return slots.stream()
                .map(slotMapper::mapToSlotResponse)
                .toList();
    }
}
