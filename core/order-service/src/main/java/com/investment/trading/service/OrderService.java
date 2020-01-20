package com.investment.trading.service;

import com.investment.trading.domain.Order;
import com.investment.trading.dto.OrderCreatedDto;
import com.investment.trading.dto.OrderCreationDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    OrderCreatedDto newOrder(OrderCreationDto orderCreationDto);

    Order findOrderById(String id);

    Order findAllOrdersByContainingSKU(String sku);

    void deleteById(String id);


}
