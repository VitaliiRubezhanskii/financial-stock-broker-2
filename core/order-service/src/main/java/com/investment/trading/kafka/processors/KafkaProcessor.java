package com.investment.trading.kafka.processors;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor  {

    String INPUT = "input0";

    String OUTPUT = "output0";

    String OUTPUT1 = "output1";

    @Output("output0")
    MessageChannel orderRequestChannel();

    @Output("output1")
    KStream<?,?> ordersChannel();

    @Input("input0")
    KStream<?,?> input();

}
