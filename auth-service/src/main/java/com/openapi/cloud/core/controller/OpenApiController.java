package com.openapi.cloud.core.controller;

import com.openapi.cloud.core.model.dto.OpenApiRequest;

import com.openapi.cloud.core.model.dto.OpenApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "OpenApi")
@RestController
@RequestMapping("/openApis")
public class OpenApiController {

    /**
     * GET /
     *
     * @return successful operation (status code 200)
     * or Unauthorized. (status code 401)
     * or Forbidden. (status code 403)
     * or Internal Server Error. (status code 500)
     * or Service Unavailable. (status code 503)
     */
    @Operation(operationId = "customerList", tags = {"Customer"}, summary = "Find all customers",
            description = "Get Customer List",
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(
                                    schema = @Schema(implementation = OpenApiResponse.class)))
                    }),
                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
                    @ApiResponse(responseCode = "404", ref = "notFoundStatus"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
            }
    )
    @GetMapping("/")
    public ResponseEntity<List<OpenApiResponse>> customerList() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * GET /{customerId}
     *
     * @param customerId (required)
     * @return successful operation (status code 200)
     * or Bad Request. (status code 400)
     * or Unauthorized. (status code 401)
     * or Forbidden. (status code 403)
     * or Internal Server Error. (status code 500)
     * or Service Unavailable. (status code 503)
     */
    @Operation(operationId = "customerById", tags = {"Customer"}, summary = "Finds customer by id",
            responses = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OpenApiResponse.class))
                    }),
                    @ApiResponse(responseCode = "400", ref = "badRequestStatus"),
                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
                    @ApiResponse(responseCode = "404", ref = "notFoundStatus"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
            }
    )
    @GetMapping("/{customerId}")
    public ResponseEntity<OpenApiResponse> customerById(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT /{customerId}
     *
     * @param customerId (required)
     * @param customer (required)
     * @return successful operation (status code 200)
     * or Bad Request. (status code 400)
     * or Unauthorized. (status code 401)
     * or Forbidden. (status code 403)
     * or Internal Server Error. (status code 500)
     * or Service Unavailable. (status code 503)
     */
    @Operation(operationId = "updateCustomerById", tags = {"Customer"}, summary = "Update an existing customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource updated successfully",
                            content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"message\" : \"Resource updated successfully\" }")})),
                    @ApiResponse(responseCode = "400", ref = "badRequestStatus"),
                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
            }
    )
    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomerById(@PathVariable("customerId") Long customerId,
                                                     @RequestBody(required = true) OpenApiRequest customer) {
        return ResponseEntity.ok("Resource updated successfully");
    }

    /**
     * POST /customer
     *
     * @param customer (required)
     * @return successful operation (status code 201)
     * or Bad Request. (status code 400)
     * or Unauthorized. (status code 401)
     * or Forbidden. (status code 403)
     * or Internal Server Error. (status code 500)
     * or Service Unavailable. (status code 503)
     */
    @Operation(operationId = "createCustomer", tags = {"Customer"}, summary = "Add new customer",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created successfully",
                            content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"customerId\": 1, \"message\": \"Resource created successfully\" }")})),
                    @ApiResponse(responseCode = "400", ref = "badRequestStatus"),
                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
            }
    )
    @PostMapping("/")
    public ResponseEntity<OpenApiResponse> createCustomer(@RequestBody(required = true) OpenApiRequest customer) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * DELETE /{customerId}
     *
     * @param customerId Customer id to delete (required)
     * @return Successful operation (status code 200)
     * or Bad Request. (status code 400)
     * or Unauthorized. (status code 401)
     * or Forbidden. (status code 403)
     * or Internal Server Error. (status code 500)
     * or Service Unavailable. (status code 503)
     */
    @Operation(operationId = "deleteCustomer", tags = {"Customer"}, summary = "Deletes a customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource deleted successfully",
                            content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"message\" : \"Resource deleted successfully\" }")})),
                    @ApiResponse(responseCode = "400", ref = "badRequestStatus"),
                    @ApiResponse(responseCode = "401", ref = "unauthorizedStatus"),
                    @ApiResponse(responseCode = "403", ref = "forbiddenStatus"),
                    @ApiResponse(responseCode = "500", ref = "internalServerErrorStatus"),
            }
    )
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok("Resource deleted successfully");
    }

}
