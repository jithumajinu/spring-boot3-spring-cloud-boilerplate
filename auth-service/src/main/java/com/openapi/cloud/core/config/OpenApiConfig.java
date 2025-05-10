package com.openapi.cloud.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Value("${openapi.url}")
    private String openAPIUrl;

    @Value("${openapi.env}")
    private String openAPIEnv;

    @Bean
    public OpenAPI baseOpenAPI() throws IOException {

        // JsonToObject readJsonFileToJsonObject = new JsonToObject();

        Server openApiServer = new Server();
        openApiServer.setUrl(openAPIUrl);
        openApiServer.setDescription(openAPIEnv);

        Info info = new Info().title("Spring Doc").version("1.0.0");

        /*
         * Client error
         * status code 400 | Bad Request
         */
        ApiResponse badRequestStatus = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("400")
                        )
                )
        ).description("Bad Request!");

        /*
         * Client error
         * status code 401 | Unauthorized
         */
        ApiResponse unauthorizedStatus = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("401")
                        )
                )
        ).description("Unauthorized!");

        /*
         * Client error
         * status code 403 | Forbidden
         */
        ApiResponse forbiddenStatus = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("403")
                        )
                )
        ).description("Forbidden!");

        /*
         * Client error
         * status code 404 | Not Found
         */
        ApiResponse notFoundStatus = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("404")
                        )
                )
        ).description("Not Found!");


        /*
         * Server error
         * status code 500 | Internal Server Error
         */
        ApiResponse internalServerErrorStatus = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("500")
                        )
                )
        ).description("Internal Server Error!");


        //Components components = new Components();

        // Define Security Scheme
        Components components = new Components()
                .addSecuritySchemes("Bearer Authentication",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Enter JWT token")
                );

        /* Client error */
        components.addResponses("badRequestStatus", badRequestStatus);
        components.addResponses("unauthorizedStatus", unauthorizedStatus);
        components.addResponses("forbiddenStatus", forbiddenStatus);
        components.addResponses("notFoundStatus", notFoundStatus);
        components.addResponses("internalServerErrorStatus", internalServerErrorStatus); // Server error

        return new OpenAPI()
                .components(components)
                .info(info)
                .servers(List.of(openApiServer));
    }
}
