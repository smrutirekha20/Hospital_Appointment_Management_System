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
import lombok.AllArgsConstructor;
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

    public DoctorResponse createDoctorProfile(Integer adminUserId,Integer departmentId,Integer specializationId, DoctorRequest doctorRequest){
         if (userRepository.existsByEmail(doctorRequest.getUser().getEmail())) {
                throw new ResourceAlreadyExistException("Email already exists");
            }
            User user = new User();
            user.setEmail(doctorRequest.getUser().getEmail());
            user.setPassword(doctorRequest.getUser().getPassword());
            user.setUserRole(UserRole.DOCTOR);

            userRepository.save(user);
        Admin admin = adminRepository.findById(adminUserId)
                .orElseThrow(()->new AdminNotFoundException("admin not found with this id "+adminUserId));
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

    public DoctorResponse updateDoctorProfile(Integer adminUserId, Integer doctorId, Integer departmentId, Integer specializationId, DoctorRequest doctorRequest) {

        Admin admin = adminRepository.findById(adminUserId)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with id " + adminUserId));

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
