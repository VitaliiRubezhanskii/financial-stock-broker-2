package com.investment.account.processor;

import com.investment.account.avro.OrderRequest;
import com.investment.account.avro.OrderResponse;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Collections;
import java.util.Map;

public class OrderRequestProcessor {


    @StreamListener("input")
    @SendTo("output")
    public KStream<String, OrderResponse> process(KStream<Object, OrderRequest> input) {

//        final Map<String, String> serdeConfig = Collections.singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
//
//        final SpecificAvroSerde<Quote> sensorSerde = new SpecificAvroSerde<>();
//        sensorSerde.configure(serdeConfig, false);

//        return input
//                .map((k, value) -> {
//                    String newKey = "v1";
//                    if (value.getId().toString().endsWith("v2")) {
//                        newKey = "v2";
//                    }
//                    return new KeyValue<String, String>(newKey, value.getTicket().toString());
//                });
          return null;
    }
}
