package com.fenrir.colorme.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Colorme Rest API",
                version = "0.1.0",
                description = "",
                contact = @Contact(
                        name = "F3NRIR",
                        email = "hitex1999@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        security = {
                @SecurityRequirement(name = "oauth2")
        }
)
public class OpenApiConfiguration {
}
