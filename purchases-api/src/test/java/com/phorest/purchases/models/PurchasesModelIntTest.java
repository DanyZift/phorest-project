package com.phorest.purchases.models;

import com.phorest.purchases.repository.PurchasesRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class PurchasesModelIntTest {

    @Autowired
    private PurchasesRepository purchasesRepository;

    @Test
    @Transactional
    public void testSaveAndRetrieveClient() {
        PurchasesModel purchase = PurchasesModel.builder()
                .id(UUID.randomUUID())
                .appointmentId(UUID.randomUUID())
                .loyaltyPoints(100)
                .name("Products")
                .price(332.22)
                .build();

        PurchasesModel savedPurchase = purchasesRepository.save(purchase);
        Optional<PurchasesModel> retrievedPurchaseOptional = purchasesRepository.findById(savedPurchase.getId());

        assertTrue(retrievedPurchaseOptional.isPresent());
        PurchasesModel retrievedPurchase = retrievedPurchaseOptional.get();
        assertEquals(purchase.getId(), retrievedPurchase.getId());
        assertEquals(purchase.getAppointmentId(), retrievedPurchase.getAppointmentId());
        assertEquals(purchase.getLoyaltyPoints(), retrievedPurchase.getLoyaltyPoints());
        assertEquals(purchase.getName(), retrievedPurchase.getName());
        assertEquals(purchase.getPrice(), retrievedPurchase.getPrice());
    }
}
