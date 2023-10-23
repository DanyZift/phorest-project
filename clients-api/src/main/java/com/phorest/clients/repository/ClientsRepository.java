package com.phorest.clients.repository;

import com.phorest.clients.models.ClientsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsModel, UUID> {}

