package com.openapi.cloud.core.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DeleteFlag {

    ACTIVE(0),
    DELETED(1);

    private final int flag;

    public int getFlag() {
        return flag;
    }


}
