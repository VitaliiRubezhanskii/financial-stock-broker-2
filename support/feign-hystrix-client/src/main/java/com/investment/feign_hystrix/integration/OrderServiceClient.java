package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.orders.OrderCreatedDto;
import com.investment.feign_hystrix.integration.domain.orders.OrderCreationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order")
@Service
public interface OrderServiceClient {

    @PostMapping(value = "/order")
    OrderCreatedDto newOrder(@RequestBody OrderCreationDto orderCreationDto);

    @GetMapping(value = "/order/{id}")
    OrderCreatedDto findOrderById(@PathVariable(value = "id") String id);

    @GetMapping(value = "/account/{accountId}")
    List<OrderCreatedDto> findOrdersByAccountId(@PathVariable(value = "accountId") String accountID);
}
