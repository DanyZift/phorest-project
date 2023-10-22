package com.phorest.clients.services;

import com.phorest.clients.models.ClientsModel;
import com.phorest.clients.repository.ClientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;

    public ClientsServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    public List<ClientsModel> getAllClients() {
        return clientsRepository.findAll();
    }

    @Override
    public ClientsModel getClientById(UUID id) {
        Optional<ClientsModel> client = clientsRepository.findById(id);
        return client.orElse(null);
    }

    @Override
    public ClientsModel createClient(ClientsModel client) {
        return clientsRepository.save(client);
    }

    @Override
    public ClientsModel updateClient(UUID id, ClientsModel client) {
        if (clientsRepository.existsById(id)) {
            client.setId(id);
            return clientsRepository.save(client);
        } else {
            return null;
        }
    }

    @Override
    public void deleteClient(UUID id) {
        if (clientsRepository.existsById(id)) {
            clientsRepository.deleteById(id);
        }
    }
}
