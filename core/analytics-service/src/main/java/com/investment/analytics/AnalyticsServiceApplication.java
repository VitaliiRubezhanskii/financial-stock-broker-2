package com.investment.analytics;

import avro.Quote;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Collections;
import java.util.Map;


@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(KafkaStreamsProcessor.class)
public class AnalyticsServiceApplication {

    private static final String STORE_NAME = "sensor-store";

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsServiceApplication.class, args);
    }

    @StreamListener("input")
    @SendTo("output")
    public KStream<String, String> process(KStream<Object, Quote> input) {

        final Map<String, String> serdeConfig = Collections.singletonMap(
                AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");

        final SpecificAvroSerde<Quote> sensorSerde = new SpecificAvroSerde<>();
        sensorSerde.configure(serdeConfig, false);

        return input
                .map((k, value) -> {
                    String newKey = "v1";
                    if (value.getId().toString().endsWith("v2")) {
                        newKey = "v2";
                    }
                    return new KeyValue<>(newKey, value.toString());
                });
//                .groupByKey()
//                .count(Materialized.<String, String, KeyValueStore<Bytes, byte[]>>as(STORE_NAME)
//                        .withKeySerde(Serdes.String())
//                        .withValueSerde(Serdes.String()))
//                .toStream();

    }
}
