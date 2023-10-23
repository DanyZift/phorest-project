package com.phorest.services.services;

import com.phorest.services.models.ServicesModel;

import java.util.List;
import java.util.UUID;

public interface ServicesService {
    List<ServicesModel> getAllServices();
    ServicesModel getServiceById(UUID id);
    ServicesModel createService(ServicesModel service);
    ServicesModel updateService(UUID id, ServicesModel service);
    void deleteService(UUID id);
}
