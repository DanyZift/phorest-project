package com.phorest.appointments.controllers;


import com.phorest.appointments.models.AppointmentsModel;
import com.phorest.appointments.services.AppointmentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentsController {
    
    private final AppointmentsService appointmentsService;
    
    public AppointmentsController(AppointmentsService appointmentsService){
        this.appointmentsService = appointmentsService;
    }

    @GetMapping
    public List<AppointmentsModel> getAllClients() {
        return appointmentsService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentsModel getClientById(@PathVariable UUID id) {
        return appointmentsService.getAppointmentById(id);
    }

    @PostMapping
    public AppointmentsModel createAppointment(@RequestBody AppointmentsModel appointment) {
        return appointmentsService.createAppointment(appointment);
    }

    @PutMapping("/{id}")
    public AppointmentsModel updateAppointment(@PathVariable UUID id, @RequestBody AppointmentsModel appointment) {
        return appointmentsService.updateAppointment(id, appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable UUID id) {
        appointmentsService.deleteAppointment(id);
    }
}
