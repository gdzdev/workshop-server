package org.gdzdev.workshop.backend.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API DOCUMENTATION")
                        .version("1.0")
                        .description("Documentación de la API de WorkshopApp")
                        .contact(new Contact()
                                .name("Aleks Gdz")
                                .email("aleksgaldamez2002@gmail.com")
                                .url("https://github.com/gdzdev")));
    }
}
