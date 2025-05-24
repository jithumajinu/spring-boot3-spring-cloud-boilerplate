package com.openapi.cloud.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RefreshScope
public class ClientController {

    @Value("${my.greeting.message}")
    private String greetingMessage;

    @Value("${app.message}")
    private String appMessage;

    @GetMapping("/client")
    public String clientCall() {
        try {
            log.info("Client ");
            return "Hi from Client";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/greeting")
    public String greeting() {
        try {
            log.info("Reading Greeting config from Config Server properties ");
            return "greeting config from config server is : "+greetingMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/message")
    public String appMessage() {
        try {
            log.info("Reading Greeting config from Local properties ");
            return "greeting config from local properties server is : "+appMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
