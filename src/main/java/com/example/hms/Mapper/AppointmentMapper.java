package com.example.hms.Mapper;


import com.example.hms.Entity.Appointment;
import com.example.hms.RequestDto.AppointmentRequest;
import com.example.hms.ResponseDto.AppointmentResponse;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public Appointment mapToAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateAndTime(appointmentRequest.getAppointmentDateAndTime());
        appointment.setAppointmentStatus(appointmentRequest.getAppointmentStatus());

        return appointment;
    }

    public AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        AppointmentResponse appointmentResponse = new AppointmentResponse();


        appointmentResponse.setAppointmentId(appointment.getAppointmentId());
        appointmentResponse.setAppointmentDateAndTime(appointment.getAppointmentDateAndTime());
        appointmentResponse.setAppointmentStatus(appointment.getAppointmentStatus());
        appointmentResponse.setDoctorName(appointment.getDoctor().getDoctorName());
        appointmentResponse.setSlotDate(appointment.getSlot().getSlotDate());
        appointmentResponse.setSlotTime(appointment.getSlot().getSlotTime());

        return appointmentResponse;
    }
}
