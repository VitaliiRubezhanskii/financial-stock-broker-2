package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.OrderCreatedDto;
import com.investment.feign_hystrix.integration.domain.OrderCreationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order")
@Service
public interface OrderServiceClient {

    @GetMapping(value = "/order/sample")
    OrderCreationDto getExample();

    @GetMapping(value = "/order/{id}")
    OrderCreationDto get(@PathVariable("id") String id);

    @PostMapping
    OrderCreatedDto create(@RequestBody OrderCreationDto orderCreationDto);
}
