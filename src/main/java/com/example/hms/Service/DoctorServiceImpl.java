package com.example.hms.Service;

import com.example.hms.Entity.*;
import com.example.hms.Enum.UserRole;
import com.example.hms.Exception.AdminNotFoundException;
import com.example.hms.Exception.DepartmentNotFoundException;
import com.example.hms.Exception.ResourceAlreadyExistException;
import com.example.hms.Exception.SpecializationNotFoundException;
import com.example.hms.Mapper.DoctorMapper;
import com.example.hms.Repository.*;
import com.example.hms.RequestDto.DoctorRequest;
import com.example.hms.ResponseDto.DoctorResponse;
import com.example.hms.Security.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final AdminRepository adminRepository;
    private final DepartmentRepository departmentRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final UserRepository userRepository;
    private final AuthUtil authUtil;

    public DoctorResponse createDoctorProfile(Integer adminId,Integer departmentId,Integer specializationId, DoctorRequest doctorRequest){
         if (userRepository.existsByEmail(doctorRequest.getUser().getEmail())) {
                throw new ResourceAlreadyExistException("Email already exists");
            }
            User user = new User();
            user.setEmail(doctorRequest.getUser().getEmail());
            user.setPassword(doctorRequest.getUser().getPassword());
            user.setUserRole(UserRole.DOCTOR);

            userRepository.save(user);
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()->new AdminNotFoundException("admin not found with this id "+adminId));
        User currentUser = authUtil.getCurrentUser();
        if (!admin.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to update this admin");
        }
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()-> new DepartmentNotFoundException("department not found with this id "+departmentId));
        Specialization specialization = specializationRepository.findById(specializationId)
                .orElseThrow(()->new SpecializationNotFoundException("Specialization not found with this id "+specializationId));
        if(!specialization.getDepartment().getDepartmentId().equals(department.getDepartmentId())){
            throw new RuntimeException("Specialization does not belong to the department");
        }
        Doctor doctor = doctorMapper.mapToDoctor(doctorRequest);
        doctor.setDepartment(department);
        doctor.setSpecialization(specialization);
        doctor.setUser(user);
        doctor.setCreatedBy(admin);

        return doctorMapper.mapToDoctorResponse(doctorRepository.save(doctor));
    }

    public DoctorResponse updateDoctorProfile(Integer adminId, Integer doctorId, Integer departmentId, Integer specializationId, DoctorRequest doctorRequest) {

        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with id " + adminId));

        User currentUser = authUtil.getCurrentUser();
        if (!admin.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to update this admin");
        }
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DepartmentNotFoundException("Doctor not found with id " + doctorId));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        Specialization specialization = specializationRepository.findById(specializationId)
                .orElseThrow(() -> new SpecializationNotFoundException("Specialization not found"));
        if (!specialization.getDepartment().getDepartmentId().equals(department.getDepartmentId())) {
            throw new RuntimeException("Specialization does not belong to the selected department");
        }

        doctor.setDoctorName(doctorRequest.getDoctorName());
        doctor.setPhoneNumber(doctorRequest.getPhoneNumber());
        doctor.setExperienceYears(doctorRequest.getExperienceYears());
        doctor.setDepartment(department);
        doctor.setSpecialization(specialization);
        doctor.setCreatedBy(admin);

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.mapToDoctorResponse(updatedDoctor);
    }
}
