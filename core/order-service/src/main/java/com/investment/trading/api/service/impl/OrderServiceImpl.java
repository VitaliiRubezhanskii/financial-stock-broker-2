package com.investment.trading.api.service.impl;

import com.investment.trading.api.repository.OrderRepository;
import com.investment.trading.api.service.OrderService;
import com.investment.trading.mapper.OrderMapper;
import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;



    @Override
    public Mono<OrderCreatedDto> newOrder(OrderCreationDto orderCreationDto) {

    return orderRepository.save(orderMapper.toEntity(orderCreationDto))
            .flatMap(t-> Mono.just(orderMapper.toDto((t))));

}
}
