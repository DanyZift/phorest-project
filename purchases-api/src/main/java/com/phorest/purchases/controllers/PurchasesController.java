package com.phorest.purchases.controllers;

import com.phorest.purchases.models.PurchasesModel;
import com.phorest.purchases.services.PurchasesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchasesController {

    private final PurchasesService purchasesService;

    public PurchasesController(PurchasesService purchasesService){
        this.purchasesService = purchasesService;
    }

    @GetMapping
    public List<PurchasesModel> getAllPurchases() {
        return purchasesService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public PurchasesModel getPurchaseById(@PathVariable UUID id) {
        return purchasesService.getPurchaseById(id);
    }

    @PostMapping
    public PurchasesModel createPurchase(@RequestBody PurchasesModel client) {
        return purchasesService.createPurchase(client);
    }

    @PutMapping("/{id}")
    public PurchasesModel updatePurchase(@PathVariable UUID id, @RequestBody PurchasesModel client) {
        return purchasesService.updatePurchase(id, client);
    }

    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable UUID id) {
        purchasesService.deletePurchase(id);
    }
}
