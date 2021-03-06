package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.orders.OrderCreatedDto;
import com.investment.feign_hystrix.integration.domain.orders.OrderCreationDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntegrationOrderController {

    private final IntegrationClient integrationClient;

    @PostMapping(value = "/order")
    public OrderCreatedDto newOrder(@RequestBody OrderCreationDto orderCreationDto){
        return integrationClient.newOrder(orderCreationDto);
    }

    @GetMapping(value = "/order/{id}")
    public OrderCreatedDto findOrderById(@PathVariable(value = "id") String id){
        return integrationClient.findOrderById(id);
    }

    @GetMapping(value = "/account/{accountId}")
    public List<OrderCreatedDto> findOrdersByAccountId(@PathVariable(value = "accountId") String accountID){
        return integrationClient.findOrdersByAccountId(accountID);
    }
}
