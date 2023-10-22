package com.phorest.clients.controllers;

import com.phorest.clients.models.ClientsModel;
import com.phorest.clients.services.ClientsService;
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
@WebMvcTest(ClientsController.class)
public class ClientsControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientsService clientsService;

    @Test
    public void testGetAllClients() throws Exception {
        when(clientsService.getAllClients()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientById() throws Exception {
        UUID clientId = UUID.randomUUID();
        ClientsModel client = new ClientsModel();

        when(clientsService.getClientById(clientId)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/" + clientId))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateClient() throws Exception {
        ClientsModel client = new ClientsModel();

        when(clientsService.createClient(client)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateClient() throws Exception {
        UUID clientId = UUID.randomUUID();
        ClientsModel client = new ClientsModel();

        when(clientsService.updateClient(clientId, client)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/clients/" + clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteClient() throws Exception {
        UUID clientId = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/" + clientId))
                .andExpect(status().isOk());
    }
}
