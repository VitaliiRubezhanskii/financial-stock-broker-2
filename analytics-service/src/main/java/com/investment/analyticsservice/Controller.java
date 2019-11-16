package com.investment.analyticsservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${analytics.check}")
    private String check;

    @GetMapping(value = "/")
    public String getCheck(){
        return check;
    }
}