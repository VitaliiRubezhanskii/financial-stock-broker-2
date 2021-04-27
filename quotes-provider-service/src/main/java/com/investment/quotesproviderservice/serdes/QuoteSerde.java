package com.investment.quotesproviderservice.serdes;


import avro.Quote;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;

import java.util.Collections;
import java.util.Map;
import org.springframework.context.annotation.Profile;

public class QuoteSerde extends SpecificAvroSerializer<Quote> {

    @Override
    public void configure(Map<String, ?> serializerConfig, boolean isSerializerForRecordKeys) {
        final Map<String, String> serdeConfig = Collections.singletonMap(
                AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
        super.configure(serdeConfig, isSerializerForRecordKeys);
    }

}
