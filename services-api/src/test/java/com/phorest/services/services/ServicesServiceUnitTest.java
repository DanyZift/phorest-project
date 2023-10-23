package com.phorest.services.services;

import com.phorest.services.models.ServicesModel;
import com.phorest.services.repository.ServicesRepository;
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
public class ServicesServiceUnitTest {

    @InjectMocks
    private ServicesServiceImpl purchasesService;

    @Mock
    private ServicesRepository purchasesRepository;

    @Test
    public void testGetAllServices() {
        when(purchasesRepository.findAll()).thenReturn(Collections.emptyList());
        List<ServicesModel> purchases = purchasesService.getAllServices();
        assertTrue(purchases.isEmpty());
    }

    @Test
    public void testGetServiceById() {
        UUID serviceId = UUID.randomUUID();
        ServicesModel service = new ServicesModel();
        service.setId(serviceId);

        when(purchasesRepository.findById(serviceId)).thenReturn(Optional.of(service));

        ServicesModel foundService = purchasesService.getServiceById(serviceId);

        assertNotNull(foundService);
        assertEquals(serviceId, foundService.getId());
    }

    @Test
    public void testGetServiceById_NonExistent() {
        UUID serviceId = UUID.randomUUID();

        when(purchasesRepository.findById(serviceId)).thenReturn(Optional.empty());

        ServicesModel foundService = purchasesService.getServiceById(serviceId);

        assertNull(foundService);
    }

    @Test
    public void testCreateService() {
        ServicesModel service = new ServicesModel();

        when(purchasesRepository.save(service)).thenReturn(service);

        ServicesModel createdService = purchasesService.createService(service);

        assertNotNull(createdService);
    }

    @Test
    public void testUpdateService() {
        UUID serviceId = UUID.randomUUID();
        ServicesModel service = new ServicesModel();
        service.setId(serviceId);

        when(purchasesRepository.existsById(serviceId)).thenReturn(true);
        when(purchasesRepository.save(service)).thenReturn(service);

        ServicesModel updatedService = purchasesService.updateService(serviceId, service);

        assertNotNull(updatedService);
        assertEquals(serviceId, updatedService.getId());
    }

    @Test
    public void testUpdateService_NonExistent() {
        UUID serviceId = UUID.randomUUID();
        ServicesModel service = new ServicesModel();
        when(purchasesRepository.existsById(serviceId)).thenReturn(false);
        ServicesModel updatedService = purchasesService.updateService(serviceId, service);
        assertNull(updatedService);
    }

    @Test
    public void testDeleteService() {
        UUID serviceId = UUID.randomUUID();
        when(purchasesRepository.existsById(serviceId)).thenReturn(true);
        purchasesService.deleteService(serviceId);
    }

    @Test
    public void testDeleteService_NonExistent() {
        UUID serviceId = UUID.randomUUID();
        when(purchasesRepository.existsById(serviceId)).thenReturn(false);
        assertDoesNotThrow(() -> purchasesService.deleteService(serviceId));
    }
}
