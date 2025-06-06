package com.example.common.controller;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public abstract class AbstractCoreUtilController {


    protected String formatInputErrors() {

        return "test data";
    }

}
