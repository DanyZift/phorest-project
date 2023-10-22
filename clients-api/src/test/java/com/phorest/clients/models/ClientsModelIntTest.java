package com.phorest.clients.models;

import com.phorest.clients.enums.GenderType;
import com.phorest.clients.repository.ClientsRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
public class ClientsModelIntTest {

    @Autowired
    private ClientsRepository clientsRepository;

    @Test
    @Transactional
    public void testSaveAndRetrieveClient() {
        ClientsModel client = ClientsModel.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone("123-456-7890")
                .gender(GenderType.MALE)
                .banned(false)
                .build();

        ClientsModel savedClient = clientsRepository.save(client);
        Optional<ClientsModel> retrievedClientOptional = clientsRepository.findById(savedClient.getId());

        assertTrue(retrievedClientOptional.isPresent());
        ClientsModel retrievedClient = retrievedClientOptional.get();
        assertEquals(client.getId(), retrievedClient.getId());
        assertEquals("John", retrievedClient.getFirstName());
        assertEquals("Doe", retrievedClient.getLastName());
        assertEquals("johndoe@example.com", retrievedClient.getEmail());
        assertEquals("123-456-7890", retrievedClient.getPhone());
        assertEquals(GenderType.MALE, retrievedClient.getGender());
        assertEquals(false, retrievedClient.getBanned());
    }

    @Test
    @Transactional
    public void testDuplicateEmail() {

        ClientsModel client1 = ClientsModel.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone("123-456-7890")
                .gender(GenderType.MALE)
                .banned(false)
                .build();

        clientsRepository.save(client1);

        assertThrows(DataIntegrityViolationException.class, () -> {
            ClientsModel duplicateEmailClient = ClientsModel.builder()
                    .id(UUID.randomUUID())
                    .firstName("Alice")
                    .lastName("Smith")
                    .email("johndoe@example.com") // Duplicate email
                    .phone("987-654-3210")
                    .gender(GenderType.FEMALE)
                    .banned(false)
                    .build();

            clientsRepository.save(duplicateEmailClient);
            assertEquals(1, clientsRepository.findAll().size());
        });
    }
}
