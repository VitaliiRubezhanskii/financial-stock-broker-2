package com.investment.serde;


import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClientConfig;
import io.confluent.kafka.schemaregistry.client.rest.RestService;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QuoteAvroSerializer implements Serializer<Object> {

    private final KafkaAvroSerializer inner;

    public QuoteAvroSerializer() {
        Map<String, String> configs = new HashMap<>();
        configs.put("schema.registry.basic.auth.user.info","4JYXYBS7AXSTQRFG:Sw4ib3vEDg4lmHFcB7p7EMsMHUiaqQkQFJHC7J5fmPXDkB3TfHPiXVWMoHyfg/qc");
        configs.put("basic.auth.credentials.source", "USER_INFO");
        RestService restService = new RestService("https://psrc-2312y.europe-west3.gcp.confluent.cloud");
        this.inner = new KafkaAvroSerializer(new CachedSchemaRegistryClient(restService, 1000, configs));
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        return this.inner.serialize(topic, data);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        final Map<String, String> serdeConfig = new HashMap<>();
        serdeConfig.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "https://psrc-2312y.europe-west3.gcp.confluent.cloud");
        serdeConfig.put(SchemaRegistryClientConfig.SCHEMA_REGISTRY_USER_INFO_CONFIG, "4JYXYBS7AXSTQRFG:Sw4ib3vEDg4lmHFcB7p7EMsMHUiaqQkQFJHC7J5fmPXDkB3TfHPiXVWMoHyfg/qc");
        serdeConfig.put(SchemaRegistryClientConfig.BASIC_AUTH_CREDENTIALS_SOURCE, "USER_INFO");
        this.inner.configure(serdeConfig, isKey);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        return this.inner.serialize(topic, data);
//        return SerializationUtils.serialize(data);
    }

    @Override
    public void close() {
        inner.close();
    }
}
