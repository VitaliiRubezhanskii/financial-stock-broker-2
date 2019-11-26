package com.investment.feign_hystrix.integration.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account", url = "http://gateway:5000")
public interface AccountServiceClient {

    @GetMapping(name = "/account/")
    String getHello();

}
