package com.example.hms.Service;

import com.example.hms.Entity.*;
import com.example.hms.Enum.AppointmentStatus;
import com.example.hms.Enum.SlotStatus;
import com.example.hms.Exception.*;
import com.example.hms.Mapper.AppointmentMapper;
import com.example.hms.Repository.*;
import com.example.hms.RequestDto.AppointmentRequest;
import com.example.hms.ResponseDto.AppointmentResponse;
import com.example.hms.Security.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointServiceImpl implements AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SlotRepository slotRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final AdminRepository adminRepository;
    private final AuthUtil authUtil;

    public AppointmentResponse bookAppointment(Integer patientUserId, String doctorName, AppointmentRequest request) {
        Patient patient = patientRepository.findByUserId(patientUserId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));
//        User currentUser = authUtil.getCurrentUser();
//        if (!patient.getUser().getId().equals(currentUser.getId())) {
//            throw new AccessDeniedException("You are not authorized to update this admin");
//        }
        Doctor doctor = doctorRepository.findByDoctorNameIgnoreCase(doctorName)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with this name " + doctorName));;

        Slot slot = slotRepository.findByDoctorAndSlotDateAndSlotTime(doctor,
                        request.getSlotDate(), request.getSlotTime())
                .orElseThrow(() -> new NoSuchSlotAvailableException("No such slot exists on selected date and time."));

        Appointment appointment = new Appointment();

        appointment.setAppointmentDateAndTime(LocalDateTime.of(request.getSlotDate(), request.getSlotTime()));
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        if (slot.getSlotStatus() == SlotStatus.AVAILABLE) {
            appointment.setSlot(slot);
            appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);

            slot.setSlotStatus(SlotStatus.UNAVAILABLE);
            slotRepository.save(slot);
        } else {
            appointment.setAppointmentStatus(AppointmentStatus.PENDING);
        }

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.mapToAppointmentResponse(savedAppointment);

    }


    public AppointmentResponse reschedulePendingAppointmentByAdmin(Integer adminId,Integer appointmentId, AppointmentRequest request) {

        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()->new AdminNotFoundException("Admin not found exception"));

        User currentUser = authUtil.getCurrentUser();
        if (!admin.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to update this admin");
        }

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (appointment.getAppointmentStatus() != AppointmentStatus.PENDING) {
            throw new RuntimeException("Only pending appointments can be rescheduled");
        }

        Doctor doctor = appointment.getDoctor();

        Slot slot = slotRepository.findByDoctorAndSlotDateAndSlotTime(doctor, request.getSlotDate(), request.getSlotTime())
                .orElseThrow(() -> new RuntimeException("Slot not available"));

        if (slot.getSlotStatus() != SlotStatus.AVAILABLE) {
            throw new RuntimeException("Slot already booked");
        }

        appointment.setSlot(slot);
        appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
        appointment.setAppointmentDateAndTime(LocalDateTime.of(request.getSlotDate(), request.getSlotTime()));

        slot.setSlotStatus(SlotStatus.UNAVAILABLE);
        slotRepository.save(slot);
        appointmentRepository.save(appointment);

        return appointmentMapper.mapToAppointmentResponse(appointment);
    }
}
