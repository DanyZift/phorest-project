package com.phorest.purchases.services;

import com.phorest.purchases.models.PurchasesModel;

import java.util.List;
import java.util.UUID;

public interface PurchasesService {
    List<PurchasesModel> getAllPurchases();
    PurchasesModel getPurchaseById(UUID id);
    PurchasesModel createPurchase(PurchasesModel client);
    PurchasesModel updatePurchase(UUID id, PurchasesModel client);
    void deletePurchase(UUID id);
}
