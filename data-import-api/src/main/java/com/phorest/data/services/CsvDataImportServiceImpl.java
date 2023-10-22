package com.phorest.data.services;


import com.phorest.clients.enums.GenderType;
import com.phorest.clients.models.ClientsModel;
import com.phorest.clients.repository.ClientsRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CsvDataImportServiceImpl implements CsvDataImportService {

    private ClientsRepository clientsRepository;

    public CsvDataImportServiceImpl(ClientsRepository clientsRepository){
        this.clientsRepository = clientsRepository;
    }

    @Override
    public void importClients(MultipartFile file) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase(true);

        try (CSVParser parser = csvFormat.parse(new InputStreamReader(file.getInputStream()))) {

            List<ClientsModel> clientsModels = new ArrayList<>();

            for(CSVRecord record : parser) {
                ClientsModel client = ClientsModel.builder()
                        .id(UUID.fromString(record.get(0)))
                        .firstName(record.get(1))
                        .lastName(record.get(2))
                        .email(record.get(3))
                        .phone(record.get(4))
                        .gender(record.get(5).equals(GenderType.MALE) ? GenderType.MALE : GenderType.FEMALE)
                        .banned(record.get(6).equalsIgnoreCase("TRUE"))
                        .build();

                clientsModels.add(client);
            }

            clientsRepository.saveAll(clientsModels);
        }
    }
}
