package com.example.hms.controller;


import com.example.hms.RequestDto.AdminRequest;
import com.example.hms.ResponseDto.AdminResponse;
import com.example.hms.Service.AdminService;
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
public class AdminController {
    private final AppResponseBuilder appResponseBuilder;
    private final AdminService adminProfileService;

    @PostMapping("/admin/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin( @RequestBody AdminRequest adminProfileRequest){
        AdminResponse adminProfileResponse = adminProfileService.createAdmin(adminProfileRequest);
        return appResponseBuilder.success(HttpStatus.CREATED,"Admin created",adminProfileResponse);
    }

    @PutMapping("/admin/{adminId}/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@PathVariable Integer adminId,@RequestBody AdminRequest adminProfileRequest){
        AdminResponse adminProfileResponse = adminProfileService.updateAdminProfile(adminId,adminProfileRequest);
        return appResponseBuilder.success(HttpStatus.CREATED,"Admin profile updated",adminProfileResponse);
    }
}
