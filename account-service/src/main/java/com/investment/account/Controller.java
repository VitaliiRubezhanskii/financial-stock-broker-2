package com.investment.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${prop.value}")
    public String prop;

    @GetMapping(value = "/")
    public String getString(){
            return prop;

    }
}
