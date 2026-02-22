package com.tobby.doggy.configuracion;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import java.util.List;

@SecurityScheme(
        name = "bearerAuth",
        description = "Token de acceso para la API",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
public class OpenApiConfiguracion {

    @Bean
    public OpenAPI personalizarOpenApi() {
        return new OpenAPI().info(
                new Info().title("KharonBlue Notas escolares")
                        .contact(new Contact().name("KharonBlue studio").url("www.kharonstudio.com/contact").email("kharon123@.com"))
                        .version("1.0.0")
                        .description("Aplicacion de administracion de puntajes escolares")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
        ).servers(List.of(
                new Server().description("Server de desarrollo").url("http://localhost:8080"),
                new Server().description("Server de producción").url("http://kharonstudio:8080")))
                ;
    }
}
