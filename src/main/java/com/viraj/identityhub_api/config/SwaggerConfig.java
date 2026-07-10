package com.viraj.identityhub_api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI identityHubOpenAPI() {

        return new OpenAPI()

                .info(new Info()
                        .title("IdentityHub REST API")
                        .description("REST API for SailPoint IdentityIQ Integration")
                        .version("1.0.0")

                        .contact(new Contact()
                                .name("Viraj Taywade")
                                .email("viraj@example.com"))

                        .license(new License()
                                .name("Apache 2.0")))

                .externalDocs(new ExternalDocumentation()
                        .description("IdentityHub Documentation"));
    }
}