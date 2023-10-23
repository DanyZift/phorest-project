package com.phorest.data.repository;

import com.phorest.data.models.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicesRepository extends JpaRepository<ServiceModel, UUID> {
}
