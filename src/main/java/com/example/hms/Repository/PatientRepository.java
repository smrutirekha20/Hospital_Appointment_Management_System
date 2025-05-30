package com.example.hms.Repository;

import com.example.hms.Entity.Patient;
import com.example.hms.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
   Optional<Patient>findByUserId(Integer userId);
}
