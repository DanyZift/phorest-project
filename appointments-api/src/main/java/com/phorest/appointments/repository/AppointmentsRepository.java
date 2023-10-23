package com.phorest.appointments.repository;

import com.phorest.appointments.models.AppointmentsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentsRepository extends JpaRepository<AppointmentsModel, UUID> {
}
