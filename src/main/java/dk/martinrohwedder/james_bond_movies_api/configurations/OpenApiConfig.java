package dk.martinrohwedder.james_bond_movies_api.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {
        final String apiKeyScheme = "ApiKeyAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("James Bond Movies API")
                        .version("1.0.0")
                        .description("REST API for James Bond movies, directors, producers and actors"))
                .addSecurityItem(new SecurityRequirement().addList(apiKeyScheme))
                .components(new Components()
                        .addSecuritySchemes(apiKeyScheme,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name("X-API-Key")));
    }
}