package it.epicode.be.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@OpenAPIDefinition(info = @Info(title = "EPIC ENERGY SERVICES CRM", version = "v1", description = "CRM interface (Token Admin di prova): eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY0NzA5MjA4NSwiZXhwIjo4ODA0NzA5MjA4NX0.f1mglxB3BVBUYlUoaCNuFlkD3-sEnh5idRFL1LTiFVtUxkv16pLstu4mrGuP3r00jwCSLsy_ZnekzOxhNLcYGw",
contact = @Contact(email = "dirubbovalerio@gmail.com")))
@Configuration
public class SwaggerConfig {

}
