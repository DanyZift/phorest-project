package com.phorest.data.repository;

import com.phorest.data.models.ClientsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsModel, UUID> { }
