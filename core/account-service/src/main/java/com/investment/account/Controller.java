package com.investment.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/account")
public class Controller {



    @GetMapping("/")
    public String getHello(){
        return "Hello from Account-service through gateway";
    }
}
