package com.example.hms.Service;

import com.example.hms.Entity.Admin;
import com.example.hms.Entity.Department;
import com.example.hms.Entity.Specialization;
import com.example.hms.Exception.AdminNotFoundException;
import com.example.hms.Exception.DepartmentNotFoundException;
import com.example.hms.Exception.SpecializationNotFoundException;
import com.example.hms.Mapper.SpecializationMapper;
import com.example.hms.Repository.AdminRepository;
import com.example.hms.Repository.DepartmentRepository;
import com.example.hms.Repository.SpecializationRepository;
import com.example.hms.RequestDto.SpecializationRequest;
import com.example.hms.ResponseDto.SpecializationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpecializationServiceImpl implements SpecializationService{

    private final AdminRepository adminRepository;
    private final DepartmentRepository departmentRepository;
    private final SpecializationMapper specializationMapper;
    private final SpecializationRepository specializationRepository;

    public SpecializationResponse createSpecialization(Integer adminUserId, Integer departmentId, SpecializationRequest request){
        Admin admin = adminRepository.findById(adminUserId)
                .orElseThrow(()->new AdminNotFoundException("admin not found with this id "+adminUserId));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("department not found with this id"+departmentId));
        Specialization specialization = specializationMapper.mapToSpecialization(request);
        specialization.setCreatedBy(admin);
        specialization.setDepartment(department);

        Specialization savedSpecialization = specializationRepository.save(specialization);
        return specializationMapper.mapToSpecializationResponse(savedSpecialization);
    }

    public SpecializationResponse updateSpecialization(Integer adminUserId, Integer departmentId, Integer specializationId, SpecializationRequest request){
        Admin admin = adminRepository.findById(adminUserId)
                .orElseThrow(()-> new AdminNotFoundException("admin not found with this id "+adminUserId));

        Specialization specialization = specializationRepository.findById(specializationId)
                .orElseThrow(()-> new SpecializationNotFoundException("Specialization not found with this id "+specializationId));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("department not found with this id "+departmentId));

        if(department != null){
            specialization.setDepartment(department);
        }
        specialization.setCreatedBy(admin);
        specialization.setSpecializationName(request.getSpecializationName());

        Specialization updatedSpecialization = specializationRepository.save(specialization);

        return specializationMapper.mapToSpecializationResponse(updatedSpecialization);

    }
}
