package com.openapi.cloud.core.controller;

import com.openapi.cloud.core.model.dto.ApiResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public abstract class AbstractCoreUtilController {

    @Autowired
    private MessageSource messageSource;

//    public String getSelectedLangCode() {
//        String langCode = LocaleContextHolder.getLocale().getLanguage();
//        if (StringUtils.isBlank(langCode)) {
//            return Locale.JAPAN.getLanguage();
//        }
//
//        if (!Locale.JAPAN.getLanguage().equals(langCode) && !Locale.US.getLanguage().equals(langCode)) {
//            return Locale.JAPAN.getLanguage();
//        }
//        return langCode;
//    }

    protected Map<String, ApiResponse.ApiError.ErrorDetail> formatInputErrors(BindingResult result) {
        Map<String, ApiResponse.ApiError.ErrorDetail> errors = Maps.newHashMap();
        System.out.println("--formatInputErrors -> Locale.getDefault :  " + Locale.getDefault());
        if (CollectionUtils.isNotEmpty(result.getAllErrors())) {
            errors = result.getAllErrors().stream()
                    .filter(FieldError.class::isInstance)
                    .map(FieldError.class::cast)
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            e -> ApiResponse.ApiError.ErrorDetail.builder()
                                    .code(e.getCodes()[e.getCodes().length - 1])
                                    .message(messageSource.getMessage(e, LocaleContextHolder.getLocale()))
                                    .build()));
        }
        return errors;
    }

}

