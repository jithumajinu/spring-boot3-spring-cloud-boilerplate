package com.openapi.cloud.core.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController extends AbstractCoreUtilController {

    /**
     * GET request to test
     *
     * @return String "Hello World!"
     */
    @GetMapping("/test")
    public String index() {
        return "Hello World!";
    }

    @PostMapping("/post-test")
    public String postTest() {
        return "postRequest!";
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/post")
    public String postRequest() {
        return "postRequest!";
    }

}
