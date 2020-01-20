package com.investment.trading.api.service;

import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;

import java.util.List;

public interface OrderService {

    OrderCreatedDto newOrder(OrderCreationDto orderCreationDto);

    OrderCreatedDto findOrderById(String id);

    List<OrderCreatedDto> findOrdersByAccountId(String accountID);
}
