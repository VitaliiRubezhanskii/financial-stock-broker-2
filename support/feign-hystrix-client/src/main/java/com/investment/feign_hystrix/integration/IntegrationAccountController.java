package com.investment.feign_hystrix.integration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IntegrationAccountController {

    private final IntegrationClient integrationClient;

    @GetMapping(name = "/account/mine")
    @HystrixCommand(fallbackMethod = "getDefaultHello")
    public String getHello(){
        return integrationClient.getHello();
    }

    public String getDefaultHello(){
        return "THis is Hello! That was wrong";
    }


}
