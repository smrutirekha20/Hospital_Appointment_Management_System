package com.example.hms.Service;

import com.example.hms.RequestDto.DepartmentRequest;
import com.example.hms.ResponseDto.DepartmentResponse;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    DepartmentResponse updateDepartment(Integer departmentId,DepartmentRequest departmentRequest);
}
