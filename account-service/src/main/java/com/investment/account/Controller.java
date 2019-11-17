package com.investment.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {



    @GetMapping("/accounts/")
    public String getHello(){
        return "Hello from Account-service through gateway";
    }
}
