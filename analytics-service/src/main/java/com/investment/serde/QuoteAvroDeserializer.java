package com.investment.serde;

import com.investment.avro.Quote;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClientConfig;
import io.confluent.kafka.schemaregistry.client.rest.RestService;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificData;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QuoteAvroDeserializer implements Deserializer<Object> {

    private final KafkaAvroDeserializer inner;

    public QuoteAvroDeserializer() {
        Map<String, String> configs = new HashMap<>();
        configs.put("schema.registry.basic.auth.user.info","4JYXYBS7AXSTQRFG:Sw4ib3vEDg4lmHFcB7p7EMsMHUiaqQkQFJHC7J5fmPXDkB3TfHPiXVWMoHyfg/qc");
        configs.put("basic.auth.credentials.source", "USER_INFO");
        RestService restService = new RestService("https://psrc-2312y.europe-west3.gcp.confluent.cloud");
        this.inner = new KafkaAvroDeserializer(new CachedSchemaRegistryClient(restService, 1000, configs));
    }

    public void configure(Map<String, ?> deserializerConfig, boolean isDeserializerForRecordKeys) {
        final Map<String, String> serdeConfig = new HashMap<>();
        serdeConfig.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "https://psrc-2312y.europe-west3.gcp.confluent.cloud");
        serdeConfig.put(SchemaRegistryClientConfig.SCHEMA_REGISTRY_USER_INFO_CONFIG, "4JYXYBS7AXSTQRFG:Sw4ib3vEDg4lmHFcB7p7EMsMHUiaqQkQFJHC7J5fmPXDkB3TfHPiXVWMoHyfg/qc");
        serdeConfig.put(SchemaRegistryClientConfig.BASIC_AUTH_CREDENTIALS_SOURCE, "USER_INFO");
        this.inner.configure(serdeConfig, isDeserializerForRecordKeys);
        this.inner.configure(deserializerConfig, isDeserializerForRecordKeys);
    }

    public Object deserialize(String topic, byte[] bytes) {
        GenericRecord record = (GenericRecord) this.inner.deserialize(topic, bytes);
        return (Quote) SpecificData.get().deepCopy(Quote.SCHEMA$, record);

    }

    public void close() {
        this.inner.close();
    }
}
