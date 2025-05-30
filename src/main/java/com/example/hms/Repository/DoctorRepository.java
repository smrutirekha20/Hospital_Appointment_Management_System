package com.example.hms.Repository;

import com.example.hms.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Optional<Doctor> findByUserId(Integer userId);
    Optional<Doctor> findByDoctorNameIgnoreCase(String doctorName);
}
