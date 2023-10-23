package com.phorest.services.controllers;

import com.phorest.services.models.ServicesModel;
import com.phorest.services.services.ServicesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/services")
public class ServicesController {

    private final ServicesService servicesService;

    public ServicesController(ServicesService servicesService){
        this.servicesService = servicesService;
    }

    @GetMapping
    public List<ServicesModel> getAllServices() {
        return servicesService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServicesModel getServiceById(@PathVariable UUID id) {
        return servicesService.getServiceById(id);
    }

    @PostMapping
    public ServicesModel createService(@RequestBody ServicesModel service) {
        return servicesService.createService(service);
    }

    @PutMapping("/{id}")
    public ServicesModel updateService(@PathVariable UUID id, @RequestBody ServicesModel service) {
        return servicesService.updateService(id, service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable UUID id) {
        servicesService.deleteService(id);
    }
}
