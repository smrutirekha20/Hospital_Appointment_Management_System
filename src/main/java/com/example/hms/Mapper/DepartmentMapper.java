package com.example.hms.Mapper;

import com.example.hms.Entity.Department;
import com.example.hms.RequestDto.DepartmentRequest;
import com.example.hms.ResponseDto.DepartmentResponse;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department mapToDepartment(DepartmentRequest request){
        Department department=new Department();
        department.setDepartmentName(request.getDepartmentName());

        return department;
    }

    public DepartmentResponse mapToDepartmentResponse(Department department){
        DepartmentResponse response=new DepartmentResponse();
        response.setDepartmentId(department.getDepartmentId());
        response.setDepartmentName(department.getDepartmentName());
        response.setCreatedBy(department.getCreatedBy().getAdminName());

        return response;
    }
}
