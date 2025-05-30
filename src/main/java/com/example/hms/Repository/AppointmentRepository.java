package com.example.hms.Repository;

import com.example.hms.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
