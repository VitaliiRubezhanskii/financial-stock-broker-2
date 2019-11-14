package com.investment.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${info.data}")
    private String test;

    @GetMapping("/")
    public String data(){
        return test;
    }

}
