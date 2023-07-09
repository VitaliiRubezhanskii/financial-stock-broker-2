package com.investment.serde;

import com.example.Sensor;
import com.investment.serializer.SensorDeserializer;
import com.investment.serializer.SensorSerializer;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SensorSerde implements Serde<Sensor> {

    private final SensorSerializer serializer;
    private final SensorDeserializer deserializer;

    public SensorSerde() {
        serializer = new SensorSerializer();
        deserializer = new SensorDeserializer();
    }

    @Override
    public Serializer<Sensor> serializer() {
        return this.serializer;
    }

    @Override
    public Deserializer<Sensor> deserializer() {
        return this.deserializer;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> serializerConfig, boolean isSerializerForRecordKeys) {
        final Map<String, String> serdeConfig = new HashMap<>();
        serdeConfig.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://strimzi-registry-operator:8081");
        serializer().configure(serdeConfig, isSerializerForRecordKeys);
        deserializer().configure(serdeConfig, isSerializerForRecordKeys);
    }

}

