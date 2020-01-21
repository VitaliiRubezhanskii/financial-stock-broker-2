package com.investment.trading.api.service.impl;

import com.investment.trading.api.repository.OrderRepository;
import com.investment.trading.api.service.OrderService;
import com.investment.trading.kafka.avro.OrderRequest;
import com.investment.trading.mapper.OrderMapper;
import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import com.investment.trading.utils.OrderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableBinding(Processor.class)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final Processor processor;

    @Override
    public OrderCreatedDto newOrder(OrderCreationDto orderCreationDto) {
        return Optional.ofNullable(orderCreationDto)
                .map(dto -> orderRepository.save(orderMapper.toEntity(dto)))
                .map(order -> {
                    OrderCreatedDto orderCreatedDto = orderMapper.toDto(order);
                    sendToKafkaTopic(OrderUtils.payloadToOrderRequest(orderCreatedDto), processor.output());
                    return orderCreatedDto;
                }).orElse(new OrderCreatedDto());


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


    private void sendToKafkaTopic(OrderRequest message, MessageChannel channel){
        channel.send(MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.MESSAGE_KEY, 1)
                .build());

    }
}
