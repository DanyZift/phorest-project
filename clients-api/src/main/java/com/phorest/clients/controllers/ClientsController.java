package com.phorest.clients.controllers;

import com.phorest.clients.models.ClientsModel;
import com.phorest.clients.services.ClientsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {

    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping
    public List<ClientsModel> getAllClients() {
        return clientsService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientsModel getClientById(@PathVariable UUID id) {
        return clientsService.getClientById(id);
    }

    @PostMapping
    public ClientsModel createClient(@RequestBody ClientsModel client) {
        return clientsService.createClient(client);
    }

    @PutMapping("/{id}")
    public ClientsModel updateClient(@PathVariable UUID id, @RequestBody ClientsModel client) {
        return clientsService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientsService.deleteClient(id);
    }
}
