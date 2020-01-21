package com.investment.account.kafka;

import com.investment.account.kafka.avro.OrderRequest;
import com.investment.account.kafka.avro.OrderResponse;
import com.investment.account.util.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@RequiredArgsConstructor
public class OrderRequestProcessor {

    private final AccountUtils accountUtils;

    @StreamListener("input")
    @SendTo("output")
    public KStream<String, OrderResponse> process(KStream<String, OrderRequest> input) {

//        final Map<String, String> serdeConfig = Collections.singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
//
//        final SpecificAvroSerde<Quote> sensorSerde = new SpecificAvroSerde<>();
//        sensorSerde.configure(serdeConfig, false);

        return input
                .filter((key, value) -> accountUtils.checkAccount(value))
                .map((k, value) ->  new KeyValue<>(k, accountUtils.mapRequestToResponse(value)));

    }



}
