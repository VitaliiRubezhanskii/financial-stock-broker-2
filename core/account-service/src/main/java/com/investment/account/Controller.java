package com.investment.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/account")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/")
    public String getHello(){
        String hello = "Hello from Account-service through gateway";
        log.info("Account Service responded with message: " + hello);
        return hello;
    }
}
