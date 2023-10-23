package com.phorest.purchases.repository;

import com.phorest.purchases.models.PurchasesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchasesRepository extends JpaRepository<PurchasesModel, UUID> {
}
