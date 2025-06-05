package com.example.hms.controller;

import com.example.hms.RequestDto.DepartmentRequest;
import com.example.hms.ResponseDto.DepartmentResponse;
import com.example.hms.Service.DepartmentService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class DepartmentController {

    private final AppResponseBuilder appResponseBuilder;
    private final DepartmentService departmentService;

    @PostMapping("/admin/departments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseStructure<DepartmentResponse>> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Department created successfully by admin", departmentResponse);
    }

    @PostMapping("/admin/{departmentId}/departments")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseStructure<DepartmentResponse>> updateDepartment(@PathVariable Integer departmentId, @RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.updateDepartment(departmentId,departmentRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Department updated successfully by admin", departmentResponse);
    }
}
