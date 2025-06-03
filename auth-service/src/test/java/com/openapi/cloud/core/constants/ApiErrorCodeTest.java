package com.openapi.cloud.core.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiErrorCodeTest {

    @Test
    void testGetLabel() {
        assertEquals("apierrorcode.input_error", ApiErrorCode.INPUT_ERROR.getLabel());
        assertEquals("apierrorcode.permission_error", ApiErrorCode.PERMISSION_ERROR.getLabel());
        assertEquals("apierrorcode.validation_error", ApiErrorCode.VALIDATION_ERROR.getLabel());
        assertEquals("apierrorcode.system_error", ApiErrorCode.SYSTEM_ERROR.getLabel());
        assertEquals("apierrorcode.not_found", ApiErrorCode.NOT_FOUND.getLabel());
        assertEquals("apierrorcode.unauthorized", ApiErrorCode.UNAUTHORIZED.getLabel());
    }
    
    @Test
    void testEnumValues() {
        ApiErrorCode[] values = ApiErrorCode.values();
        assertEquals(6, values.length);
        assertEquals(ApiErrorCode.INPUT_ERROR, values[0]);
        assertEquals(ApiErrorCode.UNAUTHORIZED, values[5]);
    }
}