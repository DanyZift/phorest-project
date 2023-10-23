package com.phorest.purchases.services;

import com.phorest.purchases.models.PurchasesModel;
import com.phorest.purchases.repository.PurchasesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PurchasesServiceUnitTest {
    
    @InjectMocks
    private PurchasesServiceImpl purchasesService;
    
    @Mock
    private PurchasesRepository purchasesRepository;

    @Test
    public void testGetAllPurchases() {
        when(purchasesRepository.findAll()).thenReturn(Collections.emptyList());
        List<PurchasesModel> purchases = purchasesService.getAllPurchases();
        assertTrue(purchases.isEmpty());
    }

    @Test
    public void testGetPurchaseById() {
        UUID clientId = UUID.randomUUID();
        PurchasesModel client = new PurchasesModel();
        client.setId(clientId);

        when(purchasesRepository.findById(clientId)).thenReturn(Optional.of(client));

        PurchasesModel foundPurchase = purchasesService.getPurchaseById(clientId);

        assertNotNull(foundPurchase);
        assertEquals(clientId, foundPurchase.getId());
    }

    @Test
    public void testGetPurchaseById_NonExistent() {
        UUID clientId = UUID.randomUUID();

        when(purchasesRepository.findById(clientId)).thenReturn(Optional.empty());

        PurchasesModel foundPurchase = purchasesService.getPurchaseById(clientId);

        assertNull(foundPurchase);
    }

    @Test
    public void testCreatePurchase() {
        PurchasesModel client = new PurchasesModel();

        when(purchasesRepository.save(client)).thenReturn(client);

        PurchasesModel createdPurchase = purchasesService.createPurchase(client);

        assertNotNull(createdPurchase);
    }

    @Test
    public void testUpdatePurchase() {
        UUID clientId = UUID.randomUUID();
        PurchasesModel client = new PurchasesModel();
        client.setId(clientId);

        when(purchasesRepository.existsById(clientId)).thenReturn(true);
        when(purchasesRepository.save(client)).thenReturn(client);

        PurchasesModel updatedPurchase = purchasesService.updatePurchase(clientId, client);

        assertNotNull(updatedPurchase);
        assertEquals(clientId, updatedPurchase.getId());
    }

    @Test
    public void testUpdatePurchase_NonExistent() {
        UUID clientId = UUID.randomUUID();
        PurchasesModel client = new PurchasesModel();
        when(purchasesRepository.existsById(clientId)).thenReturn(false);
        PurchasesModel updatedPurchase = purchasesService.updatePurchase(clientId, client);
        assertNull(updatedPurchase);
    }

    @Test
    public void testDeletePurchase() {
        UUID clientId = UUID.randomUUID();
        when(purchasesRepository.existsById(clientId)).thenReturn(true);
        purchasesService.deletePurchase(clientId);
    }

    @Test
    public void testDeletePurchase_NonExistent() {
        UUID clientId = UUID.randomUUID();
        when(purchasesRepository.existsById(clientId)).thenReturn(false);
        assertDoesNotThrow(() -> purchasesService.deletePurchase(clientId));
    }
        
        
}
