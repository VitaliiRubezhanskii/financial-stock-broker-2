package com.investment.trading.api;

import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import com.investment.trading.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController(value = "/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderCreatedDto create(@RequestBody OrderCreationDto orderCreationDto) {
       return orderService.newOrder(orderCreationDto);
    }


//
//    OrderCreatedDto findOrderById(String id);
//
//    List<OrderCreatedDto> findOrdersByAccountId(String accountID);
}
