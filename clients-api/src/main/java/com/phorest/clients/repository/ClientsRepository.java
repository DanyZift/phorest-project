package com.phorest.clients.repository;

import com.phorest.clients.models.ClientsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientsRepository extends JpaRepository<ClientsModel, UUID> { }

