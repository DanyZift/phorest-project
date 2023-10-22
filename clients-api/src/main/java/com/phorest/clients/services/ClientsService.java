package com.phorest.clients.services;

import com.phorest.clients.models.ClientsModel;

import java.util.List;
import java.util.UUID;

public interface ClientsService {
    List<ClientsModel> getAllClients();
    ClientsModel getClientById(UUID id);
    ClientsModel createClient(ClientsModel client);
    ClientsModel updateClient(UUID id, ClientsModel client);
    void deleteClient(UUID id);
}