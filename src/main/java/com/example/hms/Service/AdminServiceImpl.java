package com.example.hms.Service;

import com.example.hms.Entity.Admin;
import com.example.hms.Entity.User;
import com.example.hms.Enum.UserRole;
import com.example.hms.Exception.AdminNotFoundException;
import com.example.hms.Exception.ResourceAlreadyExistException;
import com.example.hms.Exception.UserNotFoundException;
import com.example.hms.Mapper.AdminMapper;
import com.example.hms.Repository.AdminRepository;
import com.example.hms.Repository.UserRepository;
import com.example.hms.RequestDto.AdminRequest;
import com.example.hms.ResponseDto.AdminResponse;
import com.example.hms.Security.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    private final AdminRepository adminRepository;
    private UserRepository userRepository;
    private AuthUtil authUtil;

    public AdminResponse createAdmin(AdminRequest adminRequest){

        User user = authUtil.getCurrentUser();
        // Admin currentAdmin = user.getAdmin();

        if (user.getUserRole() != UserRole.ADMIN) {
            throw  new AdminNotFoundException("Admin not found with this id");
        }

        if (user.getAdmin() != null) {
           throw new ResourceAlreadyExistException("Admin already exist");
        }

        Admin admin = adminMapper.mapToAdmin(adminRequest);
        admin.setUser(user);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.mapToAdminResponse(savedAdmin);

    }

   public AdminResponse updateAdminProfile(Integer adminId,AdminRequest request) {

//
//       User user = authUtil.getCurrentUser();
//       Admin currentAdmin = user.getAdmin();
     //  System.out.println("current user is"+currentAdmin);
         Admin admin = adminRepository.findById(adminId)
                 .orElseThrow(()->new RuntimeException(""));
        if (admin == null) {
            throw new AdminNotFoundException("admin not found with this id ");
        }
        admin.setAdminName(request.getAdminName());
        admin.setPhoneNumber(request.getPhoneNumber());
       //  currentAdmin.setUser(user);

        Admin updatedAdmin = adminRepository.save(admin);
        return adminMapper.mapToAdminResponse(updatedAdmin);
    }
}
