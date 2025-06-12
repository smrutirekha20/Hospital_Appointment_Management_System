package com.example.hms.Enum;

import com.example.hms.Entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public  enum UserRole {
    ADMIN(List.of(Privilege.ADMIN_READ
    ,Privilege.ADMIN_WRITE)),

    PATIENT(List.of(Privilege.PATIENT_WRITE,
            Privilege.PATIENT_READ,
            Privilege.ADMIN_WRITE,
            Privilege.ADMIN_READ)),

    DOCTOR(List.of(Privilege.DOCTOR_READ,
            Privilege.DOCTOR_WRITE,
            Privilege.ADMIN_WRITE,
            Privilege.ADMIN_READ));

    private List<Privilege> privileges;
}
