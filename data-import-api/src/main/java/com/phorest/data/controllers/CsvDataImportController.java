package com.phorest.data.controllers;

import com.phorest.data.services.CsvDataImportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/clients")
    @Operation(
            summary = "Upload a csv clients file",
            description = "Upload a file to the server.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(
                                    type = "string",
                                    format = "binary"
                            )
                    )
            )
    )
    public ResponseEntity<String> importCSV(@PathVariable String type, @RequestParam MultipartFile file){
        try {
            csvDataImportService.importClients(file);
            return ResponseEntity.ok("CSV data imported for " + type);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during csv import: " + e.getMessage());
        }
    }
}
