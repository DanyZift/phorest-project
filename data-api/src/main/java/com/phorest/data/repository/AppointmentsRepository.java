package com.phorest.data.repository;

import com.phorest.data.models.AppointmentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentsRepository extends JpaRepository<AppointmentsModel, UUID> {
}
