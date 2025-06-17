package com.example.hms.controller;


import com.example.hms.RequestDto.AdminRequest;
import com.example.hms.ResponseDto.AdminResponse;
import com.example.hms.Service.AdminService;
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
public class AdminController {
    private final AppResponseBuilder appResponseBuilder;
    private final AdminService adminProfileService;

    @Operation(description = "The end point can be used to create admin", responses =
            {
                    @ApiResponse(responseCode = "200", description = "Admin create"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PostMapping("/admin/{id}/user")
    @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@PathVariable Integer id, @RequestBody AdminRequest adminProfileRequest){
        AdminResponse adminProfileResponse = adminProfileService.createAdmin(id,adminProfileRequest);
        return appResponseBuilder.success(HttpStatus.CREATED,"Admin created",adminProfileResponse);
    }
    @Operation(description = "The end point can be used to update admin", responses =
            {
                    @ApiResponse(responseCode = "200", description = "admin updated"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PutMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN_WRITE')")
    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminProfileRequest){
        AdminResponse adminProfileResponse = adminProfileService.updateAdminProfile(adminProfileRequest);
        return appResponseBuilder.success(HttpStatus.CREATED,"Admin profile updated",adminProfileResponse);
    }
}
