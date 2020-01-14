package com.investment.trading.processor;

import com.investment.trading.avro.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Processor.class)
@Slf4j
public class OrderCreationEventProcessor {

    @StreamListener(Processor.INPUT)
    public void consumeEmployeeDetails(Order order) {
        log.info("Let's process employee details: {}", order);
    }
}
