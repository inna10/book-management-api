package com.inna.book_management_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: Configuration class for OpenAPI documentation of the Book Management API.
 */
@Configuration
public class OpenApiConfig {
    /**
     * Configures OpenAPI documentation for the Book Management API.
     *
     * @return OpenAPI instance with API information.
     */
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
