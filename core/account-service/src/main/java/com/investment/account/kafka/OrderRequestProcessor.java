package com.investment.account.kafka;

import com.investment.account.kafka.avro.OrderRequest;
import com.investment.account.kafka.avro.OrderResponse;
import com.investment.account.util.AccountUtils;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@EnableBinding(KafkaStreamsProcessor.class)
public class OrderRequestProcessor {

    private final AccountUtils accountUtils;

    @StreamListener("input")
    @SendTo("output")
    public KStream<String, String> process(KStream<String, OrderRequest> input) {

        final Map<String, String> serdeConfig = Collections.singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
        final SpecificAvroSerde<OrderRequest> orderRequestSpecificAvroSerde = new SpecificAvroSerde<>();
        final SpecificAvroSerde<OrderResponse> orderResponseSpecificAvroSerde = new SpecificAvroSerde<>();
        orderRequestSpecificAvroSerde.configure(serdeConfig, false);
        orderResponseSpecificAvroSerde.configure(serdeConfig, false);

        return input
                .filter((key, value) -> accountUtils.checkAccount(value))
                .map((k, value) ->  new KeyValue<>(k, value.getTicket().toString()));

    }



}
