package com.example.CRUDPostgres.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up Swagger/OpenAPI documentation.
 * This class configures the OpenAPI 3.0 specification for the Employee Management API.
 * It enables the generation of the API documentation and provides an interactive Swagger UI
 * for testing the endpoints.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configures the OpenAPI 3.0 specification for the Employee Management API.
     * The API will have a title, version, and description that can be seen on the Swagger UI.
     *
     * @return OpenAPI object containing API metadata.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management API")      // The title of the API
                        .version("1.0")                        // The version of the API
                        .description("API for managing employees and departments"));  // Description of the API
    }
}
