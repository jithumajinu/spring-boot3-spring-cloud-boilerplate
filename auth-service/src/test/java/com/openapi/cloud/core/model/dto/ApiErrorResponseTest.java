package com.openapi.cloud.core.model.dto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorResponseTest {

    @Test
    void testBuilder() {
        LocalDateTime now = LocalDateTime.now();
        ApiErrorResponse response = ApiErrorResponse.builder()
                .timestamp(now)
                .status(404)
                .error("Not Found")
                .message("Resource not found")
                .path("/api/resource")
                .errorCode("ERR001")
                .build();

        assertEquals(now, response.getTimestamp());
        assertEquals(404, response.getStatus());
        assertEquals("Not Found", response.getError());
        assertEquals("Resource not found", response.getMessage());
        assertEquals("/api/resource", response.getPath());
        assertEquals("ERR001", response.getErrorCode());
    }

    @Test
    void testStaticFactoryMethod() {
        String message = "Resource not found";
        String path = "/api/resource";
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiErrorResponse response = ApiErrorResponse.of(status, message, path);

        assertNotNull(response.getTimestamp());
        assertEquals(404, response.getStatus());
        assertEquals("Not Found", response.getError());
        assertEquals(message, response.getMessage());
        assertEquals(path, response.getPath());
        assertNull(response.getErrorCode());
    }
}