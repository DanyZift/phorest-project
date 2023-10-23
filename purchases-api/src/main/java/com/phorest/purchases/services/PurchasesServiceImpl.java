package com.phorest.purchases.services;

import com.phorest.purchases.models.PurchasesModel;
import com.phorest.purchases.repository.PurchasesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchasesServiceImpl implements PurchasesService {

    private final PurchasesRepository purchasesRepository;

    public PurchasesServiceImpl(PurchasesRepository purchasesRepository){
        this.purchasesRepository = purchasesRepository;
    }
    @Override
    public List<PurchasesModel> getAllPurchases() {
        return purchasesRepository.findAll();
    }

    @Override
    public PurchasesModel getPurchaseById(UUID id) {
        Optional<PurchasesModel> purchase = purchasesRepository.findById(id);
        return purchase.orElse(null);
    }

    @Override
    public PurchasesModel createPurchase(PurchasesModel purchase) {
        return purchasesRepository.save(purchase);
    }

    @Override
    public PurchasesModel updatePurchase(UUID id, PurchasesModel purchase) {
        if (purchasesRepository.existsById(id)){
            purchase.setId(id);
            return purchasesRepository.save(purchase);
        }else{
            return null;
        }
    }

    @Override
    public void deletePurchase(UUID id) {
        if(purchasesRepository.existsById(id)){
            purchasesRepository.deleteById(id);
        }
    }
}
