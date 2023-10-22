package com.phorest.data.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CsvDataImportService {
    void importClients(MultipartFile file) throws IOException;
}
