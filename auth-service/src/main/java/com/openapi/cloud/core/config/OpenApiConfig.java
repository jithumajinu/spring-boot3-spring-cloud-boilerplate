package com.openapi.cloud.core.config;

import com.openapi.cloud.core.util.JsonToObject;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
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

    @Value("${aig.openapi.url}")
    private String openAPIUrl;

    @Value("${aig.openapi.env}")
    private String openAPIEnv;

    @Bean
    public OpenAPI baseOpenAPI() throws IOException {

        JsonToObject readJsonFileToJsonObject = new JsonToObject();

        Server openApiServer = new Server();
        openApiServer.setUrl(openAPIUrl);
        openApiServer.setDescription(openAPIEnv);

        Info info = new Info()
                .title("Spring Doc")
                .version("1.0.0");  // release version?

        /*
         * Client error
         * status code 400 | Bad Request
         */
        ApiResponse badRequestStatus = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value(readJsonFileToJsonObject.read().get("badRequestStatus").toString())
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
                                new Example().value(readJsonFileToJsonObject.read().get("unauthorizedStatus").toString())
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
                                new Example().value(readJsonFileToJsonObject.read().get("forbiddenStatus").toString())
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
                                new Example().value(readJsonFileToJsonObject.read().get("notFoundStatus").toString())
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
                                new Example().value(readJsonFileToJsonObject.read().get("internalServerErrorStatus").toString())
                        )
                )
        ).description("Internal Server Error!");


        Components components = new Components();

        /* Client error */
        components.addResponses("badRequestStatus", badRequestStatus);
        components.addResponses("unauthorizedStatus", unauthorizedStatus);
        components.addResponses("forbiddenStatus", forbiddenStatus);
        components.addResponses("notFoundStatus", notFoundStatus);

        /* Server error */
        components.addResponses("internalServerErrorStatus", internalServerErrorStatus);

        return new OpenAPI()
                .components(components)
                .info(info)
                .servers(List.of(openApiServer));
    }
}
