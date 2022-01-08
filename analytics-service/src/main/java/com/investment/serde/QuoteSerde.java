package com.investment.serde;


import com.investment.avro.Quote;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClientConfig;
import io.confluent.kafka.schemaregistry.client.rest.RestService;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroDeserializer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
import org.apache.avro.generic.GenericData;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class QuoteSerde implements Serde<Object> {

    private final QuoteAvroSerializer serializer;
    private final QuoteAvroDeserializer deserializer;

    public QuoteSerde() {
        serializer = new QuoteAvroSerializer();
        deserializer = new QuoteAvroDeserializer();
    }

    @Override
    public Serializer<Object> serializer() {
        return this.serializer;
    }

    @Override
    public Deserializer<Object> deserializer() {
        return this.deserializer;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> serializerConfig, boolean isSerializerForRecordKeys) {
        final Map<String, String> serdeConfig = new HashMap<>();
        serdeConfig.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "https://psrc-2312y.europe-west3.gcp.confluent.cloud");
        serdeConfig.put(SchemaRegistryClientConfig.SCHEMA_REGISTRY_USER_INFO_CONFIG, "4JYXYBS7AXSTQRFG:Sw4ib3vEDg4lmHFcB7p7EMsMHUiaqQkQFJHC7J5fmPXDkB3TfHPiXVWMoHyfg/qc");
        serdeConfig.put(SchemaRegistryClientConfig.BASIC_AUTH_CREDENTIALS_SOURCE, "USER_INFO");

        serializer().configure(serdeConfig, isSerializerForRecordKeys);
        deserializer().configure(serdeConfig, isSerializerForRecordKeys);
    }

}
