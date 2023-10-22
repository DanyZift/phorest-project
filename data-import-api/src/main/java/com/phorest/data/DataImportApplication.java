package com.phorest.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.phorest.clients.models",
        "com.phorest.clients.repository"
})
public class DataImportApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataImportApplication.class, args);
    }
}
