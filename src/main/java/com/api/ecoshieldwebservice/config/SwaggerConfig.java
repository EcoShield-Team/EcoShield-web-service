package com.api.ecoshieldwebservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ecoShieldOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("🌿 EcoShield Web Service API")
                        .description("API para gestión de usuarios, blogs, posts, comentarios y feedback del sistema EcoShield.")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Coloca aquí tu token JWT obtenido desde /auth/login")))
                .addTagsItem(new Tag().name("Auth").description("Endpoints públicos para autenticación y registro"))
                .addTagsItem(new Tag().name("Blog").description("Gestión de blogs y subida de imágenes a Cloudinary"))
                .addTagsItem(new Tag().name("Feedback").description("Gestión de comentarios y sugerencias"));
    }
}
