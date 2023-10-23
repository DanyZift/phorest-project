package com.phorest.services.repository;

import com.phorest.services.models.ServicesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServicesRepository extends JpaRepository<ServicesModel, UUID> {}
