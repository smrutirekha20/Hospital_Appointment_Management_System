package com.example.hms.Repository;

import com.example.hms.Entity.Patient;
import com.example.hms.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
   Optional<Patient>findByUserId(Integer userId);

   @Query(value = "SELECT * FROM patients LIMIT :limit OFFSET :offset",nativeQuery = true)
   List<Patient> findPatientByPagination(@Param("limit") int limit,@Param("offset") int offset);

   @Query(value = "SELECT Count(*) FROM patients",nativeQuery = true)
   long countAllPatients();
}
