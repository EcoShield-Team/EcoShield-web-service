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
                        .description("""
                                API para la plataforma EcoShield 🌱  
                                Incluye gestión de usuarios, blogs, posts, comentarios, feedback y el almanaque de plagas y enfermedades y detección IA.  
                                Autenticación mediante JWT Bearer Token.
                                """)
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Coloca aquí tu token JWT obtenido desde /auth/login")))
                .addTagsItem(new Tag().name("Auth").description("🔐 Endpoints de autenticación, registro y recuperación de contraseñas"))
                .addTagsItem(new Tag().name("Usuario").description("👤 Gestión de usuarios: consulta, edición, eliminación y asignación de roles"))
                .addTagsItem(new Tag().name("Post").description("📝 Creación, edición y consulta de publicaciones de los usuarios"))
                .addTagsItem(new Tag().name("Comentario").description("💬 Gestión de comentarios asociados a los posts"))
                .addTagsItem(new Tag().name("Blog").description("📚 Blogs informativos: tips y noticias sobre agricultura sostenible"))
                .addTagsItem(new Tag().name("Feedback").description("🗣️ Opiniones, sugerencias y calificaciones del sistema"))
                .addTagsItem(new Tag().name("Almanaque").description("🌾 Catálogo de plagas y enfermedades agrícolas con filtros y búsquedas"))
                .addTagsItem(new Tag().name("Deteccion").description("🔎 Detección automática de plagas y enfermedades mediante inteligencia artificial aplicada a imágenes"))
                .addTagsItem(new Tag().name("Clima").description("⛅ Servicio que obtiene el clima actual según coordenadas geográficas o nombre de ciudad"));
    }
}