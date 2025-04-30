package com.openapi.cloud.core.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DeleteFlag {

    VALID(0),
    INVALID(1);

    private final int flag;

    public int getFlag() {
        return flag;
    }


}
