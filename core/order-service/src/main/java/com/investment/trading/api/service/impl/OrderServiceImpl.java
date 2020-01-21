package com.investment.trading.api.service.impl;

import com.investment.trading.api.repository.OrderRepository;
import com.investment.trading.api.service.OrderService;
import com.investment.trading.mapper.OrderMapper;
import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public OrderCreatedDto findOrderById(String id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public List<OrderCreatedDto> findOrdersByAccountId(String accountID) {
         return orderRepository.findOrdersByAccount(accountID)
                 .stream()
                 .map(orderMapper::toDto)
                 .collect(Collectors.toList());
    }
}
