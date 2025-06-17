package com.example.hms.Service;

import com.example.hms.RequestDto.DepartmentRequest;
import com.example.hms.ResponseDto.DepartmentResponse;

public interface DepartmentService {
    DepartmentResponse createDepartment(Integer adminUserId,DepartmentRequest departmentRequest);
    DepartmentResponse updateDepartment(Integer adminId,Integer departmentId,DepartmentRequest departmentRequest);
}
