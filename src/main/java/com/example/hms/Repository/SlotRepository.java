package com.example.hms.Repository;

import com.example.hms.Entity.Doctor;
import com.example.hms.Entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {

    @Query("SELECT s FROM Slot s " +
            "WHERE s.slotStatus = 'AVAILABLE' " +
            "AND (:doctorName IS NULL OR LOWER(s.doctor.doctorName) LIKE LOWER(CONCAT('%', :doctorName, '%'))) " )
    List<Slot> getAvailableSlots(@Param("doctorName") String doctorName);

    Optional<Slot> findByDoctorAndSlotDateAndSlotTime(Doctor doctor, LocalDate slotDate, LocalTime slotTime);

    @Query(value = "SELECT * FROM slots WHERE doctor_user_id = :doctorId LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Slot> findSlotsByDoctorIdWithPagination(@Param("doctorId") Integer doctorId,
                                                 @Param("limit") int limit,
                                                 @Param("offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM slots WHERE doctor_user_id = :doctorId", nativeQuery = true)
    long countByDoctorId(@Param("doctorId") Integer doctorId);


    @Query(value = """
    SELECT * FROM slots s 
    WHERE s.slot_status = 'AVAILABLE'
      AND (:datePart IS NULL OR CAST(s.slot_date AS CHAR) LIKE %:datePart%)
      AND (:doctorId IS NULL OR s.doctor_user_id = :doctorId)
""", nativeQuery = true)
    List<Slot> findAvailableSlotsByPartialDate(@Param("datePart") String datePart,
                                               @Param("doctorId") Integer doctorId);
}
