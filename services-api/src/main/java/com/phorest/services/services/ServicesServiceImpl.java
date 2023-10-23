package com.phorest.services.services;

import com.phorest.services.models.ServicesModel;
import com.phorest.services.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServicesServiceImpl implements ServicesService{

    private final ServicesRepository servicesRepository;

    public ServicesServiceImpl(ServicesRepository servicesRepository){
        this.servicesRepository = servicesRepository;
    }

    @Override
    public List<ServicesModel> getAllServices() {
        return this.servicesRepository.findAll();
    }

    @Override
    public ServicesModel getServiceById(UUID id) {
        Optional<ServicesModel> service =  this.servicesRepository.findById(id);
        return service.orElse(null);
    }

    @Override
    public ServicesModel createService(ServicesModel service) {
        return this.servicesRepository.save(service);
    }

    @Override
    public ServicesModel updateService(UUID id, ServicesModel service) {
        if(this.servicesRepository.existsById(id)){
            service.setId(id);
            return this.servicesRepository.save(service);
        }else{
            return null;
        }
    }

    @Override
    public void deleteService(UUID id) {
        if(this.servicesRepository.existsById(id)){
            this.servicesRepository.deleteById(id);
        }
    }
}
