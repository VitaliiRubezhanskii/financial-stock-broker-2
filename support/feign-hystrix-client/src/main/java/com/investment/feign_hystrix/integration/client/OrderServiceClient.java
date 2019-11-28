package com.investment.feign_hystrix.integration.client;

import com.investment.feign_hystrix.integration.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "order")
public interface OrderServiceClient {

    @GetMapping(value = "/order/sample")
    Order getExample();

    @GetMapping(value = "/order/{id}")
    Order get(@PathVariable("id") String id);

    @PostMapping(value = "/order")
    Order create(@RequestBody Order order);
}
