package com.example.hms.controller;


import com.example.hms.RequestDto.AdminRequest;
import com.example.hms.ResponseDto.AdminResponse;
import com.example.hms.Service.AdminService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${hospital.base_url}")
@AllArgsConstructor
public class AdminController {
    private final AppResponseBuilder appResponseBuilder;
    private final AdminService adminProfileService;

    @PostMapping("/admin/{userId}/user")
    public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@PathVariable Integer userId, @RequestBody AdminRequest adminProfileRequest){
        AdminResponse adminProfileResponse = adminProfileService.createAdmin(userId,adminProfileRequest);
        return appResponseBuilder.success(HttpStatus.CREATED,"Admin created",adminProfileResponse);
    }

    @PutMapping("/admin/{userId}/user")
    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@PathVariable Integer userId, @RequestBody AdminRequest adminProfileRequest){
        AdminResponse adminProfileResponse = adminProfileService.updateAdminProfile(userId,adminProfileRequest);
        return appResponseBuilder.success(HttpStatus.CREATED,"Admin profile updated",adminProfileResponse);
    }
}
