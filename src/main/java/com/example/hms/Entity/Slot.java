package com.example.hms.Entity;

import com.example.hms.Enum.SlotStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "slots")
@Data
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id")
    private Integer slotId;

    @Column(name = "slot_date")
    private LocalDate slotDate;

    @Column(name = "slot_time")
    private LocalTime slotTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "slot_status")
    private SlotStatus slotStatus = SlotStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "doctor_user_id")
    private Doctor doctor;

//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @ManyToOne
//    @JoinColumn(name = "specialization_id")
//    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "admin_user_id")
    private Admin createdBy;
}
