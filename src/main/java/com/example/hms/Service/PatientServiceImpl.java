package com.example.hms.Service;

import com.example.hms.Entity.Admin;
import com.example.hms.Entity.Patient;
import com.example.hms.Entity.User;
import com.example.hms.Enum.UserRole;
import com.example.hms.Exception.AdminNotFoundException;
import com.example.hms.Exception.PatientNotFoundException;
import com.example.hms.Exception.ResourceAlreadyExistException;
import com.example.hms.Mapper.PatientMapper;
import com.example.hms.Repository.AdminRepository;
import com.example.hms.Repository.PatientRepository;
import com.example.hms.Repository.UserRepository;
import com.example.hms.RequestDto.PatientRequest;
import com.example.hms.ResponseDto.PatientResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public PatientResponse registerPatient(Integer userId,PatientRequest patientRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(user.getUserRole()!=UserRole.PATIENT){
            throw new PatientNotFoundException("Patient not found with userId "+userId);
        }
        if (user.getPatient() != null) {
            throw new ResourceAlreadyExistException("Patient already exist");
        }
        Patient patient = patientMapper.mapToPatient(patientRequest);
        patient.setUser(user);

        return patientMapper.mapToPatientResponse(patientRepository.save(patient));
    }

    public PatientResponse updatePatientByAdmin(Integer adminUserId,
                                                    Integer patientUserId, PatientRequest request) {

        Admin admin = adminRepository.findById(adminUserId)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + adminUserId));

        Patient patient = patientRepository.findById(patientUserId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + patientUserId));

        if (patient != null){
            patient.setPatientName(request.getPatientName());
            patient.setAge(request.getAge());
            patient.setGender(request.getGender());
            patient.setPhoneNumber(request.getPhoneNumber());

            Patient updatedPatient = patientRepository.save(patient);
        }

        return patientMapper.mapToPatientResponse(patientRepository.save(patient));
    }
    public PageResponse<PatientResponse> getAllPatient(Integer page, Integer size) {
        int defaultPageSize = 10;
        int pageSize = (size == null || size <= 0) ? defaultPageSize : size;
        int pageNumber = (page == null || page <= 0) ? 1 : page;

        int offset = (pageNumber - 1) * pageSize;

        List<Patient> patients = patientRepository.findPatientByPagination(pageSize, offset);
        long total = patientRepository.countAllPatients();

        List<PatientResponse> responses = patients.stream()
                .map(patientMapper::mapToPatientResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                responses,
                pageNumber,
                pageSize,
                total,
                (int) Math.ceil((double) total / pageSize),
                pageNumber * pageSize >= total
        );


    }
}

