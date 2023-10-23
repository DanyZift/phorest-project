package com.phorest.data.repository;

import com.phorest.data.models.PurchasesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchasesRepository extends JpaRepository<PurchasesModel, UUID> {
}
