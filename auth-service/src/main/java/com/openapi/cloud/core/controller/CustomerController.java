package com.openapi.cloud.core.controller;

import com.openapi.cloud.core.constants.AuthorizationTypePatterns;
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

@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController extends AbstractCoreUtilController {

    /**
     * GET request to test
     *
     * @return String "Hello World!"
     */

    //  @PreAuthorize("hasRole('USER')")
    @GetMapping("/edit")
    public String edit() {


        System.out.println("--------------------");

        System.out.println(AuthorizationTypePatterns.CUSTOMER_EDIT.name());  // CUSTOMER_EDIT     Role
        System.out.println(AuthorizationTypePatterns.CUSTOMER_EDIT.getApiCode());  //   /api/customer/edit  api
        System.out.println(AuthorizationTypePatterns.CUSTOMER_EDIT.getApiPathPattern()); //    /api/customer/edit**
        System.out.println(AuthorizationTypePatterns.CUSTOMER_FUNCTION.getApiPathPattern()); //    /api/customer/**

        System.out.println("--------------------");

        return "customer - edit !";
    }

    //   @PreAuthorize("hasRole('USER')")
    @GetMapping("/add")
    public String add() {
        return "customer - add";
    }

    //   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String delete() {
        return "customer - delete";
    }


    //   @PreAuthorize("hasRole('USER')")
    @GetMapping("/getbyid")
    public String getById() {
        return "customer - getById";
    }


    //   @PostMapping("/post-test")
    public String postTest() {
        return "postRequest!";
    }

    //   @PreAuthorize("hasRole('USER')")
    @PostMapping("/post")
    public String postRequest() {
        return "postRequest!";
    }

}
