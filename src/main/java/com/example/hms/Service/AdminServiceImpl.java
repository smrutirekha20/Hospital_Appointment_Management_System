package com.example.hms.Service;

import com.example.hms.Entity.Admin;
import com.example.hms.Entity.User;
import com.example.hms.Enum.UserRole;
import com.example.hms.Exception.AdminNotFoundException;
import com.example.hms.Exception.ResourceAlreadyExistException;
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
    private final AuthUtil authUtil;

    public AdminResponse createAdmin(Integer id,AdminRequest adminRequest){

        User currentUser = authUtil.getCurrentUser();

       if(!currentUser.getId().equals(id)){
           throw new AdminNotFoundException("You are not authorized to create admin for this user");
       }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getUserRole() != UserRole.ADMIN) {
            throw  new AdminNotFoundException("Admin not found ");
        }
        if (user.getAdmin() != null) {
            throw new ResourceAlreadyExistException("Admin already exist");
        }

        Admin admin = adminMapper.mapToAdmin(adminRequest);
        admin.setUser(user);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.mapToAdminResponse(savedAdmin);

    }

   public AdminResponse updateAdminProfile(AdminRequest request) {


       User currentUser = authUtil.getCurrentUser();
       Admin admin = adminRepository.findByUser(currentUser)
               .orElseThrow(() -> new AdminNotFoundException("Admin not found for this user"));
        admin.setAdminName(request.getAdminName());
        admin.setPhoneNumber(request.getPhoneNumber());

        Admin updatedAdmin = adminRepository.save(admin);
        return adminMapper.mapToAdminResponse(updatedAdmin);
    }
}
