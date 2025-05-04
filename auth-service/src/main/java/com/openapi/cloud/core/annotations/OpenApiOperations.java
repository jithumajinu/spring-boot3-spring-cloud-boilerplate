package com.openapi.cloud.core.annotations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.ExampleObject;
//import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Tag(name = "OpenApi")
public interface OpenApiOperations {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(
                                    schema = @Schema(implementation = com.openapi.cloud.core.model.dto.ApiResponse.class)))
                    }),
                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
                    @ApiResponse(responseCode = "404", ref = "notFoundStatus"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
            }
    )
    public @interface OpenApiListOperation {
        String tags() default "xxxxx";

        String summary() default "xxxxx";

        String description() default "xxxxx";

        String operationId() default "xxxxx";

        boolean isPaginated() default false;

        boolean sortable() default false;

        String[] additionalTags() default {};
    }


    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.openapi.cloud.core.model.dto.ApiResponse.class))
                    }),
                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
                    @ApiResponse(responseCode = "404", ref = "notFoundStatus"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
            }
    )
    public @interface OpenApiObjectOperation {
        String tags() default "xxxxx";

        String summary() default "xxxxx";

        String description() default "xxxxx";

        String operationId() default "xxxxx";

        boolean isPaginated() default false;

        boolean sortable() default false;

        String[] additionalTags() default {};
    }


//    @Operation(operationId = "customerById", tags = {"Customer"}, summary = "Finds customer by id",
//            responses = {
//                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = OpenApiResponse.class))
//                    }),
//                    @ApiResponse(responseCode = "400", ref = "badRequestStatus"),
//                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
//                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
//                    @ApiResponse(responseCode = "404", ref = "notFoundStatus"),
//                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
//            }
//    )
//    @GetMapping("/{customerId}")
//    public ResponseEntity<OpenApiResponse> customerById(@PathVariable("customerId") String customerId) {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//


    // @Operation(operationId = "customerList", tags = {"Customer"}, summary = "Find all customers",
//            description = "Get Customer List",
//            responses = {
//                    @ApiResponse(responseCode = "200", content = {
//                            @Content(mediaType = "application/json", array = @ArraySchema(
//                                    schema = @Schema(implementation = OpenApiResponse.class)))
//                    }),
//                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
//                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
//                    @ApiResponse(responseCode = "404", ref = "notFoundStatus"),
//                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
//            }
//    )

//    @Operation(operationId = "updateCustomerById", tags = {"Customer"}, summary = "Update an existing customer",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Resource updated successfully",
//                            content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"message\" : \"Resource updated successfully\" }")})),
//                    @ApiResponse(responseCode = "400", ref = "badRequestStatus"),
//                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
//                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
//                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
//            }
//    )

}
