package com.example.hms.controller;

import com.example.hms.RequestDto.DepartmentRequest;
import com.example.hms.ResponseDto.DepartmentResponse;
import com.example.hms.Service.DepartmentService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ErrorStructure;
import com.example.hms.utility.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(description = "The end point can be used to create department", responses =
            {
                    @ApiResponse(responseCode = "200", description = "department updated"),
                    @ApiResponse(responseCode = "404", description = "adminId not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PostMapping("/admin/{adminId}/departments")
    @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public ResponseEntity<ResponseStructure<DepartmentResponse>> createDepartment(@PathVariable Integer adminId, @RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.createDepartment( adminId,departmentRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Department created successfully by admin", departmentResponse);
    }
    @Operation(description = "The end point can be used to update the department", responses =
            {
                    @ApiResponse(responseCode = "200", description = "department updated"),
                    @ApiResponse(responseCode = "404", description = "adminId not found"),
                    @ApiResponse(responseCode = "404", description = "departmentId not found"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PutMapping("/admin/{adminId}/{departmentId}/departments")
    @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public ResponseEntity<ResponseStructure<DepartmentResponse>> updateDepartment(@PathVariable Integer adminId,@PathVariable Integer departmentId, @RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.updateDepartment( adminId,departmentId,departmentRequest);
        return  appResponseBuilder.success(HttpStatus.CREATED, "Department updated successfully by admin", departmentResponse);
    }
}
