package com.investment.trading.api;

import com.investment.trading.api.repository.OrderRepository;
import com.investment.trading.api.service.OrderService;
import com.investment.trading.mapper.OrderMapper;
import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@RestController(value = "/order")
@RequiredArgsConstructor
@EnableBinding({Processor.class})
@Slf4j
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final Processor processor;

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/events")
    public Flux<OrderCreatedDto> events(@RequestBody List<OrderCreatedDto> dtos) {
        orderRepository.save(orderMapper.fromCreatedToEntity(dtos.get(0))).subscribe(data -> log.info(data.getId()));
         return Flux.fromIterable(dtos).flatMap(t-> {

             processor.output().send(MessageBuilder.withPayload(t).build());
             return Flux.fromIterable(dtos);
         });
    }

}
