package com.marketplace.ms_notificaciones.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de Swagger / OpenAPI para ms-notificaciones.
 * Swagger UI: http://localhost:PUERTO/swagger-ui.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS-Notificaciones API")
                        .version("1.0")
                        .description("Notificaciones transversales del sistema"));
    }
}
