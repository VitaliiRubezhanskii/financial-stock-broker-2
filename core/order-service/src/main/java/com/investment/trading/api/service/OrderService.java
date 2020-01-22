package com.investment.trading.api.service;

import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderService {

    Mono<OrderCreatedDto> newOrder(OrderCreationDto orderCreationDto);

//    OrderCreatedDto findOrderById(String id);
//
//    List<OrderCreatedDto> findOrdersByAccountId(String accountID);
}
