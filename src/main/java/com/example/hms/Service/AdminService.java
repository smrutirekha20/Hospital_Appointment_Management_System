package com.example.hms.Service;

import com.example.hms.RequestDto.AdminRequest;
import com.example.hms.ResponseDto.AdminResponse;

public interface AdminService {
   AdminResponse createAdmin(Integer id,AdminRequest adminProfileRequest);
   AdminResponse updateAdminProfile(AdminRequest adminRequest);
}
