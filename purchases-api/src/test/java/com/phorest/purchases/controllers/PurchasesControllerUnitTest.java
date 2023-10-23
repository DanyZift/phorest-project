package com.phorest.purchases.controllers;

import com.phorest.purchases.models.PurchasesModel;
import com.phorest.purchases.services.PurchasesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchasesController.class)
public class PurchasesControllerUnitTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchasesService purchasesService;

    @Test
    public void testGetAllPurchases() throws Exception {
        when(purchasesService.getAllPurchases()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/purchases"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientById() throws Exception {
        UUID clientId = UUID.randomUUID();
        PurchasesModel client = new PurchasesModel();

        when(purchasesService.getPurchaseById(clientId)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/purchases/" + clientId))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateClient() throws Exception {
        PurchasesModel purchase = new PurchasesModel();

        when(purchasesService.createPurchase(purchase)).thenReturn(purchase);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/purchases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateClient() throws Exception {
        UUID purchaseId = UUID.randomUUID();
        PurchasesModel purchase = new PurchasesModel();

        when(purchasesService.updatePurchase(purchaseId, purchase)).thenReturn(purchase);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/purchases/" + purchaseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteClient() throws Exception {
        UUID purchaseId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/purchases/" + purchaseId))
                .andExpect(status().isOk());
    }
}
