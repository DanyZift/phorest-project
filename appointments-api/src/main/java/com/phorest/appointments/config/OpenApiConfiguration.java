package com.phorest.appointments.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Value("${server.port}")
    private int serverPort;

    @Bean
    public OpenAPI documentOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Appointments API")
                        .description("Appointments API")
                        .version("1.0.0")
                );
    }
}
