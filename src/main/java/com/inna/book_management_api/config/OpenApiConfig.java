package com.inna.book_management_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book Management API")
                        .description("REST API for managing books")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Inna")
                                .email("inna.eisen@gmail.com")));
    }
}
