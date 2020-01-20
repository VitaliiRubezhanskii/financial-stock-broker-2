package com.investment.trading.service.Impl;

import com.investment.trading.domain.Order;
import com.investment.trading.dto.OrderCreatedDto;
import com.investment.trading.dto.OrderCreationDto;
import com.investment.trading.mapper.OrderMapper;
import com.investment.trading.repository.OrderRepository;
import com.investment.trading.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public OrderCreatedDto newOrder(OrderCreationDto orderCreationDto) {
        return Optional.ofNullable(orderCreationDto)
                .map(dto -> orderRepository.save(orderMapper.toEntity(dto)))
                .map(orderMapper::toDto)
                .orElse(new OrderCreatedDto());

    }

    @Override
    public Order findOrderById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public Order findAllOrdersByContainingSKU(String sku) {
        return orderRepository.findOrderByItemsContaining(sku);
    }

    @Override
    public void deleteById(String id) {
         orderRepository.deleteById(id);
    }
}
