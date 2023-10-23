package com.phorest.appointments.services;

import com.phorest.appointments.models.AppointmentsModel;
import com.phorest.appointments.repository.AppointmentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentsServiceImpl implements AppointmentsService{

    private final AppointmentsRepository appointmentsRepository;

    public AppointmentsServiceImpl(AppointmentsRepository appointmentsRepository){
        this.appointmentsRepository = appointmentsRepository;
    }

    @Override
    public List<AppointmentsModel> getAllAppointments() {
        return this.appointmentsRepository.findAll();
    }

    @Override
    public AppointmentsModel getAppointmentById(UUID id) {
        Optional<AppointmentsModel> appointment = this.appointmentsRepository.findById(id);
        return appointment.orElse(null);
    }

    @Override
    public AppointmentsModel createAppointment(AppointmentsModel appointment) {
        return this.appointmentsRepository.save(appointment);
    }

    @Override
    public AppointmentsModel updateAppointment(UUID id, AppointmentsModel appointment) {
        if(appointmentsRepository.existsById(id)){
            appointment.setId(id);
            return appointmentsRepository.save(appointment);
        }else{
            return null;
        }
    }

    @Override
    public void deleteAppointment(UUID id) {
        if(appointmentsRepository.existsById(id)){
            appointmentsRepository.deleteById(id);
        }else{
            //handle not exists here ...
        }
    }
}
