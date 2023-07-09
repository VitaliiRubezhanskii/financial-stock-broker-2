package com.investment.serializer;

import com.example.Sensor;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Arrays;
import java.util.Map;

public class SensorDeserializer implements Deserializer<Sensor> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Sensor deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }

    @Override
    public Sensor deserialize(String topic, byte[] data) {
        try {
            Sensor result = null;

            if (data != null) {

                DatumReader<Sensor> datumReader = new SpecificDatumReader<>(Sensor.SCHEMA$);
                Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);

                result = (Sensor) datumReader.read(null, decoder);
            }
            return result;
        } catch (Exception ex) {
            throw new SerializationException(
                    "Can't deserialize data '" + Arrays.toString(data) + "' from topic '" + topic + "'", ex);
        }
    }
}
