package com.openapi.cloud.core.exception;

import com.openapi.cloud.core.model.dto.ApiErrorResponse;
import com.openapi.cloud.core.model.dto.ApiResponse.ApiError;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleProductNotFound(
            ProductNotFoundException ex,
            HttpServletRequest request) {

        log.error("ProductNotFoundException occurred at [{}] - Message: {}, StackTrace:",
                request.getRequestURI(),
                ex.getMessage(),
                ex);
        ApiErrorResponse response = ApiErrorResponse.of(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI()
        );
        response.setErrorCode("PRODUCT_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        log.error("AccessDeniedException occurred at [{}] - Message: {}",
                request.getRequestURI(),
                ex.getMessage());

        ApiErrorResponse response = ApiErrorResponse.of(
                HttpStatus.FORBIDDEN,
                "access_denied, You don't have permission to access this resource",
                request.getRequestURI()
        );

        response.setErrorCode("INTERNAL_ERROR");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    // Catch-all (optional)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request) {
        log.error("ProductNotFoundException occurred at [{}] - Message: {}, StackTrace:",
                request.getRequestURI(),
                ex.getMessage(),
                ex);
        ApiErrorResponse response = ApiErrorResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getRequestURI()
        );
        response.setErrorCode("INTERNAL_ERROR");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
