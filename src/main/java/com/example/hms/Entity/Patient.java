package com.example.hms.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "patients")
@Data
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "patientName")
    private String patientName;


    @Column(name = "phone_no")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @OneToOne
    @JoinColumn(name = "patient_user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "admin_user_id")
//    private Admin updatedBy;
}
