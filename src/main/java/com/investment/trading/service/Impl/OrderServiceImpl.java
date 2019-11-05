package com.investment.trading.service.Impl;

import com.investment.trading.domain.Order;
import com.investment.trading.repository.OrderRepository;
import com.investment.trading.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Mono<Order> newOrder(Order order) {
    Order o = new Order();
    o.setItems(order.getItems());
    return orderRepository.save(o);
    }

    @Override
    public Mono<Order> getOrder(String id) {
        return orderRepository.findById(id);
    }
}
