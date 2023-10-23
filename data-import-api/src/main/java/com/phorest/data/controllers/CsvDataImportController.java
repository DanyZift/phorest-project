package com.phorest.data.controllers;

import com.phorest.data.services.CsvDataImportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/import")
public class CsvDataImportController {

    private final CsvDataImportService csvDataImportService;

    public CsvDataImportController(CsvDataImportService csvDataImportService) {
        this.csvDataImportService = csvDataImportService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = {"/clients"},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> importClients(@RequestPart(value = "file") final MultipartFile file){
        try {
            csvDataImportService.importClients(file);
            return ResponseEntity.ok("{\"success\":\"CSV data imported for clients\"}");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during csv import: " + e.getMessage());
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = {"/appointments"},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> importAppointments(@RequestParam("file") MultipartFile file){
        try {
            csvDataImportService.importAppointments(file);
            return ResponseEntity.ok("{\"success\":\"CSV data imported for appointments\"}");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during csv import: " + e.getMessage());
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = {"/services"},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> importServices(@RequestParam("file") MultipartFile file){
        try {
            csvDataImportService.importServices(file);
            return ResponseEntity.ok("{\"success\":\"CSV data imported for services\"}");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during csv import: " + e.getMessage());
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = {"/purchases"},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> importPurchases(@RequestParam("file") MultipartFile file){
        try {
            csvDataImportService.importPurchases(file);
            return ResponseEntity.ok("{\"success\":\"CSV data imported for purchases\"}");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during csv import: " + e.getMessage());
        }
    }
}
