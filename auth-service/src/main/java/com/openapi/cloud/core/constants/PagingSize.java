package com.openapi.cloud.core.constants;

//import io.crm.app.core.annotation.LocalizedLabel;
//import io.crm.app.core.service.EnumResourceService;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PagingSize {
    SIZE_10(10),
    SIZE_30(30),
    SIZE_50(50),
    SIZE_100(100),
    SIZE_200(200);

    @Getter
    private final Integer code;
}
