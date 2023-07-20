package com.exadel.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
    info = @Info(
            contact = @Contact(
                    name = "Pavel",
                    email = "pashoktitov16@gmail.com",
                    url = "https://github.com/PavelTitov16"
            ),
            description = "Open Api for Insurance Application",
            title = "OpenApi specification",
            version = "1.0"
    ),
        servers = {
            @Server(
                    description = "Local ENV",
                    url = "http://localhost:8080"
            )
        }
)
public class OpenApiConfig {
}
