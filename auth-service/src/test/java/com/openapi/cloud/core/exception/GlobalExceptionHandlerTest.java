package com.openapi.cloud.core.exception;

import com.openapi.cloud.core.model.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getRequestURI()).thenReturn("/api/test");
    }

    @Test
    void handleProductNotFoundException() {
        // Arrange
        String errorMessage = "Product not found";
        ProductNotFoundException exception = new ProductNotFoundException(errorMessage);

        // Act
        ResponseEntity<ApiErrorResponse> responseEntity = exceptionHandler.handleProductNotFound(exception, request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ApiErrorResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals(errorMessage, response.getMessage());
        assertEquals("/api/test", response.getPath());
        assertEquals("PRODUCT_NOT_FOUND", response.getErrorCode());
    }

    @Test
    void handleAccessDeniedException() {
        // Arrange
        AccessDeniedException exception = new AccessDeniedException("Access denied");

        // Act
        ResponseEntity<ApiErrorResponse> responseEntity = exceptionHandler.handleAccessDeniedException(exception, request);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        ApiErrorResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
        assertEquals("access_denied, You don't have permission to access this resource", response.getMessage());
        assertEquals("/api/test", response.getPath());
        assertEquals("INTERNAL_ERROR", response.getErrorCode());
    }

    @Test
    void handleGenericException() {
        // Arrange
        String errorMessage = "Something went wrong";
        Exception exception = new RuntimeException(errorMessage);

        // Act
        ResponseEntity<ApiErrorResponse> responseEntity = exceptionHandler.handleGenericException(exception, request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ApiErrorResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals(errorMessage, response.getMessage());
        assertEquals("/api/test", response.getPath());
        assertEquals("INTERNAL_ERROR", response.getErrorCode());
    }
}