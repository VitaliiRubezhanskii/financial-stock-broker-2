package com.investment.trading.service.Impl;

import com.investment.trading.domain.Order;
import com.investment.trading.repository.OrderRepository;
import com.investment.trading.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Mono<Order> newOrder(Order order) {
    return orderRepository.save(order);
    }

    @Override
    public Mono<Order> findOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Flux<Order> findAllOrdersByContainingSKU(String sku) {
        return orderRepository.findOrderByItemsContaining(sku);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return orderRepository.deleteById(id);
    }
}
