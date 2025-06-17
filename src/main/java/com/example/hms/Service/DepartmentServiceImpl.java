package com.example.hms.Service;

import com.example.hms.Entity.Admin;
import com.example.hms.Entity.Department;
import com.example.hms.Entity.User;
import com.example.hms.Exception.AdminNotFoundException;
import com.example.hms.Exception.DepartmentNotFoundException;
import com.example.hms.Mapper.DepartmentMapper;
import com.example.hms.Repository.AdminRepository;
import com.example.hms.Repository.DepartmentRepository;
import com.example.hms.RequestDto.DepartmentRequest;
import com.example.hms.ResponseDto.DepartmentResponse;
import com.example.hms.Security.AuthUtil;
import lombok.AllArgsConstructor;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{


    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;
    private final AdminRepository adminRepository;
    private final AuthUtil authUtil;

    public DepartmentResponse createDepartment(Integer adminId, DepartmentRequest departmentRequest){
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new AdminNotFoundException("admin not found with this id "+adminId));
        User currentUser = authUtil.getCurrentUser();
        if (!admin.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to update this admin");
        }
        Department department = departmentMapper.mapToDepartment(departmentRequest);
        department.setCreatedBy(admin);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.mapToDepartmentResponse(savedDepartment);
    }
    public DepartmentResponse updateDepartment(Integer adminUserId,Integer departmentId,DepartmentRequest request){
        Admin admin = adminRepository.findById(adminUserId)
                .orElseThrow(()-> new AdminNotFoundException("Admin not found with this id "+adminUserId));

        User currentUser = authUtil.getCurrentUser();

        if (!admin.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to update this admin");
        }
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("department not found with this id "+departmentId));

        department.setDepartmentName(request.getDepartmentName());
        department.setCreatedBy(admin);

       Department updatedDepartment = departmentRepository.save(department);

       return departmentMapper.mapToDepartmentResponse(updatedDepartment);

    }
}

