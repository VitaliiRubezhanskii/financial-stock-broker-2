package com.investment.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaPropertiesConfigurationBeanPostProcessor implements BeanPostProcessor {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof KafkaProperties) {
            KafkaProperties properties = (KafkaProperties) bean;

            AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
//                    .withCredentials(new ProfileCredentialsProvider("default"))
                    .withRegion("eu-central-1").build();
            String kafkaSecretsAsString = client.getSecretValue(new GetSecretValueRequest().withSecretId("dev/App/secret")).getSecretString();
            AwsKafkaSecrets kafkaSecrets = null;
            try {
               kafkaSecrets = objectMapper.readValue(kafkaSecretsAsString, AwsKafkaSecrets.class);
            } catch (JsonProcessingException e) {
                log.error("ERROR!!" + e.getMessage());
            }
            final String jaasPropKey = "sasl.jaas.config";
            String jaasPropKeyAsString = String.format(properties.getProperties().get(jaasPropKey), kafkaSecrets.getUsername(), kafkaSecrets.getPassword());
            properties.getProperties().put(jaasPropKey, jaasPropKeyAsString);
            return properties;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
