package com.phorest.clients.services;

import com.phorest.clients.models.ClientsModel;
import com.phorest.clients.repository.ClientsRepository;
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
public class ClientServiceUnitTest {
    @InjectMocks
    private ClientsServiceImpl clientsService;

    @Mock
    private ClientsRepository clientsRepository;

    @Test
    public void testGetAllClients() {
        when(clientsRepository.findAll()).thenReturn(Collections.emptyList());
        List<ClientsModel> clients = clientsService.getAllClients();
        assertTrue(clients.isEmpty());
    }

    @Test
    public void testGetClientById() {
        UUID clientId = UUID.randomUUID();
        ClientsModel client = new ClientsModel();
        client.setId(clientId);

        when(clientsRepository.findById(clientId)).thenReturn(Optional.of(client));

        ClientsModel foundClient = clientsService.getClientById(clientId);

        assertNotNull(foundClient);
        assertEquals(clientId, foundClient.getId());
    }

    @Test
    public void testGetClientById_NonExistent() {
        UUID clientId = UUID.randomUUID();

        when(clientsRepository.findById(clientId)).thenReturn(Optional.empty());

        ClientsModel foundClient = clientsService.getClientById(clientId);

        assertNull(foundClient);
    }

    @Test
    public void testCreateClient() {
        ClientsModel client = new ClientsModel();

        when(clientsRepository.save(client)).thenReturn(client);

        ClientsModel createdClient = clientsService.createClient(client);

        assertNotNull(createdClient);
    }

    @Test
    public void testUpdateClient() {
        UUID clientId = UUID.randomUUID();
        ClientsModel client = new ClientsModel();
        client.setId(clientId);

        when(clientsRepository.existsById(clientId)).thenReturn(true);
        when(clientsRepository.save(client)).thenReturn(client);

        ClientsModel updatedClient = clientsService.updateClient(clientId, client);

        assertNotNull(updatedClient);
        assertEquals(clientId, updatedClient.getId());
    }

    @Test
    public void testUpdateClient_NonExistent() {
        UUID clientId = UUID.randomUUID();
        ClientsModel client = new ClientsModel();
        when(clientsRepository.existsById(clientId)).thenReturn(false);
        ClientsModel updatedClient = clientsService.updateClient(clientId, client);
        assertNull(updatedClient);
    }

    @Test
    public void testDeleteClient() {
        UUID clientId = UUID.randomUUID();
        when(clientsRepository.existsById(clientId)).thenReturn(true);
        clientsService.deleteClient(clientId);
    }

    @Test
    public void testDeleteClient_NonExistent() {
        UUID clientId = UUID.randomUUID();
        when(clientsRepository.existsById(clientId)).thenReturn(false);
        assertDoesNotThrow(() -> clientsService.deleteClient(clientId));
    }
}
