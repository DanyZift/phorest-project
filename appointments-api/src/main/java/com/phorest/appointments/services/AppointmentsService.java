package com.phorest.appointments.services;

import com.phorest.appointments.models.AppointmentsModel;

import java.util.List;
import java.util.UUID;

public interface AppointmentsService {
    List<AppointmentsModel> getAllAppointments();
    AppointmentsModel getAppointmentById(UUID id);
    AppointmentsModel createAppointment(AppointmentsModel client);
    AppointmentsModel updateAppointment(UUID id, AppointmentsModel client);
    void deleteAppointment(UUID id);
}
