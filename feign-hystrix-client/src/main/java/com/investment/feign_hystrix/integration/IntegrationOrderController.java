package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.orders.OrderCreatedDto;
import com.investment.feign_hystrix.integration.domain.orders.OrderCreationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IntegrationOrderController {

    private final IntegrationClient integrationClient;

    public IntegrationOrderController(IntegrationClient integrationClient) {
        this.integrationClient = integrationClient;
    }

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
