package com.investment.feign_hystrix;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
@EnableCircuitBreaker
//@EnableOAuth2Sso
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FeignHystrixClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignHystrixClientApplication.class, args);
    }

}
