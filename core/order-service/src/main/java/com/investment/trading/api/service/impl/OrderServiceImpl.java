package com.investment.trading.api.service.impl;

import avro.OrderRequest;
import com.investment.trading.api.repository.OrderRepository;
import com.investment.trading.api.service.OrderService;
import com.investment.trading.mapper.OrderMapper;
import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
//@EnableBinding(Processor.class)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;



    @Override
    public Mono<OrderCreatedDto> newOrder(OrderCreationDto orderCreationDto) {

    return orderRepository.save(orderMapper.toEntity(orderCreationDto))
            .flatMap(t-> Mono.just(orderMapper.toDto((t))));

//        return orderRepository.save(orderMapper.toEntity(orderCreationDto))
//                .flatMap(t -> {
////                    sendToKafkaTopic(payloadToOrderRequest(orderMapper.toDto(t)), processor.output());
//                    return Mono.just(orderMapper.toDto(t));
//                });


//        return Optional.ofNullable(orderCreationDto)
//                .map(dto -> orderRepository.save(orderMapper.toEntity(dto)))
//                .map(order -> {
//                    OrderCreatedDto orderCreatedDto = orderMapper.toDto(order);
//                    sendToKafkaTopic(OrderUtils.payloadToOrderRequest(orderCreatedDto), processor.output());
//                    return orderCreatedDto;
//                }).orElse(new OrderCreatedDto());


    }

//    @Override
//    public OrderCreatedDto findOrderById(String id) {
//        return orderRepository.findById(id)
//                .map(orderMapper::toDto)
//                .orElseThrow(() -> new RuntimeException("Not Found"));
//    }
//
//    @Override
//    public List<OrderCreatedDto> findOrdersByAccountId(String accountID) {
//         return orderRepository.findOrdersByAccount(accountID)
//                 .stream()
//                 .map(orderMapper::toDto)
//                 .collect(Collectors.toList());
//    }


    private void sendToKafkaTopic(OrderRequest message, MessageChannel channel){
        channel.send(MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.MESSAGE_KEY, 1)
                .build());

    }
}
