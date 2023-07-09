package com.investment.api.service;

import com.investment.model.dto.OrderCreatedDto;
import com.investment.model.dto.OrderCreationDto;

import java.util.List;

public interface OrderService {

    OrderCreatedDto newOrder(OrderCreationDto orderCreationDto);

    OrderCreatedDto findOrderById(String id);

    List<OrderCreatedDto> findOrdersByAccountId(String accountID);
}
