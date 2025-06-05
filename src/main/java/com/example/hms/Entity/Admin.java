package com.example.hms.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "admins")
@Data
public class Admin{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name="admin_name")
    private String adminName;

    @Column(name = "phn_number")
    private String phoneNumber;

//    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
//    private List<Specialization> specializations;
//
    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL)
    private List<Department> departments;

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL)
    private List<Doctor> doctors;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
