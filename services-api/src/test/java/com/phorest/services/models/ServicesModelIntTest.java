package com.phorest.services.models;

import com.phorest.services.repository.ServicesRepository;
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
public class ServicesModelIntTest {
    
    @Autowired
    private ServicesRepository servicesRepository;

    @Test
    @Transactional
    public void testSaveAndRetrieveClient() {
        ServicesModel purchase = ServicesModel.builder()
                .id(UUID.randomUUID())
                .appointmentId(UUID.randomUUID())
                .loyaltyPoints(100)
                .name("Products")
                .price(332.22)
                .build();

        ServicesModel savedPurchase = servicesRepository.save(purchase);
        Optional<ServicesModel> retrievedPurchaseOptional = servicesRepository.findById(savedPurchase.getId());

        assertTrue(retrievedPurchaseOptional.isPresent());
        ServicesModel retrievedPurchase = retrievedPurchaseOptional.get();
        assertEquals(purchase.getId(), retrievedPurchase.getId());
        assertEquals(purchase.getAppointmentId(), retrievedPurchase.getAppointmentId());
        assertEquals(purchase.getLoyaltyPoints(), retrievedPurchase.getLoyaltyPoints());
        assertEquals(purchase.getName(), retrievedPurchase.getName());
        assertEquals(purchase.getPrice(), retrievedPurchase.getPrice());
    }
}
