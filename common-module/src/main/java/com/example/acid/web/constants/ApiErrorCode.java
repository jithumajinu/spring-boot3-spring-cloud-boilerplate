package com.example.acid.web.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiErrorCode {

    INPUT_ERROR,
    PERMISSION_ERROR,
    VALIDATION_ERROR,
    SYSTEM_ERROR,
    NOT_FOUND,
    UNAUTHORIZED;

    public String getLabel() {
        return ApiErrorCode.class.getSimpleName().toLowerCase() + "." + name().toLowerCase();
    }
}

