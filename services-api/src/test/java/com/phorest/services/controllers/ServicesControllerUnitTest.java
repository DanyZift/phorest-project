package com.phorest.services.controllers;


import com.phorest.services.models.ServicesModel;
import com.phorest.services.services.ServicesService;
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
@WebMvcTest(ServicesController.class)
public class ServicesControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicesService servicesService;

    @Test
    public void testGetAllServices() throws Exception {
        when(servicesService.getAllServices()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/services"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientById() throws Exception {
        UUID clientId = UUID.randomUUID();
        ServicesModel client = new ServicesModel();

        when(servicesService.getServiceById(clientId)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/services/" + clientId))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateClient() throws Exception {
        ServicesModel purchase = new ServicesModel();

        when(servicesService.createService(purchase)).thenReturn(purchase);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/services")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateClient() throws Exception {
        UUID purchaseId = UUID.randomUUID();
        ServicesModel purchase = new ServicesModel();

        when(servicesService.updateService(purchaseId, purchase)).thenReturn(purchase);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/services/" + purchaseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteClient() throws Exception {
        UUID purchaseId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/services/" + purchaseId))
                .andExpect(status().isOk());
    }

}
