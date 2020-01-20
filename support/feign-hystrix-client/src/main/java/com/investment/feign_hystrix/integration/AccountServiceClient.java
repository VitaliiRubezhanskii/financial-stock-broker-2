package com.investment.feign_hystrix.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account")
@Service
public interface AccountServiceClient {

    @GetMapping(name = "/account/")
    String getHello();

}
