package com.phorest.data.services;

import com.phorest.data.enums.GenderType;
import com.phorest.data.models.AppointmentsModel;
import com.phorest.data.models.ClientsModel;
import com.phorest.data.models.PurchasesModel;
import com.phorest.data.models.ServicesModel;
import com.phorest.data.repository.AppointmentsRepository;
import com.phorest.data.repository.ClientsRepository;
import com.phorest.data.repository.PurchasesRepository;
import com.phorest.data.repository.ServicesRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CsvDataImportServiceImpl implements CsvDataImportService {

    private final ClientsRepository clientsRepository;
    private final AppointmentsRepository appointmentsRepository;
    private final ServicesRepository servicesRepository;
    private final PurchasesRepository purchasesRepository;

    @Autowired
    public CsvDataImportServiceImpl(ClientsRepository clientsRepository,
                                    AppointmentsRepository appointmentsRepository,
                                    ServicesRepository servicesRepository,
                                    PurchasesRepository purchasesRepository){
        this.clientsRepository = clientsRepository;
        this.appointmentsRepository = appointmentsRepository;
        this.servicesRepository = servicesRepository;
        this.purchasesRepository = purchasesRepository;
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

    @Override
    public void importAppointments(MultipartFile file) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase(true);

        try (CSVParser parser = csvFormat.parse(new InputStreamReader(file.getInputStream()))) {

            List<AppointmentsModel> appointmentsModels = new ArrayList<>();

            for(CSVRecord record : parser) {

                AppointmentsModel appointment = AppointmentsModel.builder()
                        .id(UUID.fromString(record.get(0)))
                        .clientId(UUID.fromString(record.get(1)))
                        .startTime(convertDateTime(record.get(2)))
                        .endTime(convertDateTime(record.get(3)))
                        .build();

                appointmentsModels.add(appointment);

            }
            appointmentsRepository.saveAll(appointmentsModels);
        }
    }

    @Override
    public void importServices(MultipartFile file) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase(true);

        try (CSVParser parser = csvFormat.parse(new InputStreamReader(file.getInputStream()))) {

            List<ServicesModel> serviceModels = new ArrayList<>();

            for(CSVRecord record : parser) {

                ServicesModel service = ServicesModel.builder()
                        .id(UUID.fromString(record.get(0)))
                        .appointmentId(UUID.fromString(record.get(1)))
                        .name(record.get(2))
                        .price(Double.valueOf(record.get(3)))
                        .loyaltyPoints(Integer.valueOf(record.get(4)))
                        .build();

                serviceModels.add(service);

            }
            servicesRepository.saveAll(serviceModels);
        }
    }

    @Override
    public void importPurchases(MultipartFile file) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase(true);

        try (CSVParser parser = csvFormat.parse(new InputStreamReader(file.getInputStream()))) {

            List<PurchasesModel> purchasesModels = new ArrayList<>();

            for(CSVRecord record : parser) {

                PurchasesModel purchase = PurchasesModel.builder()
                        .id(UUID.fromString(record.get(0)))
                        .appointmentId(UUID.fromString(record.get(1)))
                        .name(record.get(2))
                        .price(Double.valueOf(record.get(3)))
                        .loyaltyPoints(Integer.valueOf(record.get(4)))
                        .build();

                purchasesModels.add(purchase);

            }
            purchasesRepository.saveAll(purchasesModels);
        }
    }

    private LocalDateTime convertDateTime(final String inputDate){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDate, inputFormatter);
        return zonedDateTime.toLocalDateTime();
    }


}
