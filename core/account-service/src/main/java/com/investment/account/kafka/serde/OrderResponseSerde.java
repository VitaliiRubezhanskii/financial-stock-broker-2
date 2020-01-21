package com.investment.account.kafka.serde;

import com.investment.account.kafka.avro.OrderResponse;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;

import java.util.Collections;
import java.util.Map;

public class OrderResponseSerde extends SpecificAvroSerializer<OrderResponse> {

    @Override
    public void configure(Map<String, ?> serializerConfig, boolean isSerializerForRecordKeys) {
        final Map<String, String> serdeConfig = Collections.singletonMap(
                AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
        super.configure(serdeConfig, isSerializerForRecordKeys);
    }
}