package com.example.hms.Mapper;

import com.example.hms.Entity.Admin;
import com.example.hms.RequestDto.AdminRequest;
import com.example.hms.ResponseDto.AdminResponse;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin mapToAdmin(AdminRequest request){
        Admin adminProfile = new Admin();
        adminProfile.setAdminName(request.getAdminName());
        adminProfile.setPhoneNumber(request.getPhoneNumber());

        return adminProfile;
    }
    public AdminResponse mapToAdminResponse(Admin admin){
        AdminResponse response = new AdminResponse();
        response.setAdminId(admin.getAdminId());
        response.setAdminName(admin.getAdminName());
        response.setPhoneNumber(admin.getPhoneNumber());

        return response;
    }
}
