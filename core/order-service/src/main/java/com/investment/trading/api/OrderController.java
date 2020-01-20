package com.investment.trading.controller;

import com.investment.trading.dto.OrderCreatedDto;
import com.investment.trading.dto.OrderCreationDto;
import com.investment.trading.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderCreatedDto create(@RequestBody OrderCreationDto orderCreationDto) {
       return orderService.newOrder(orderCreationDto);
    }
}
