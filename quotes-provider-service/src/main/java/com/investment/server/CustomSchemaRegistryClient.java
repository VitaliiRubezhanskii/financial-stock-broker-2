//package com.investment.quotesproviderservice.server;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.cloud.schema.registry.SchemaReference;
//import org.springframework.cloud.schema.registry.SchemaRegistrationResponse;
//import org.springframework.cloud.schema.registry.client.SchemaRegistryClient;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
////@Component
//public class CustomSchemaRegistryClient implements SchemaRegistryClient {
//
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    private String endpoint = "http://schema-registry:8081";
//
//
//    public CustomSchemaRegistryClient() {
//        this.restTemplate = new RestTemplateBuilder().build();
//        Assert.notNull(restTemplate, "'restTemplate' must not be null.");
//    }
//
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    @Override
//    public SchemaRegistrationResponse register(String subject, String format, String schema) {
//        Map<String, String> requestBody = new HashMap<>();
////        requestBody.put("subject", subject);
////        System.out.println("subject = " + subject);
////        requestBody.put("format", format);
//        System.out.println("format = " + format);
//        requestBody.put("schema", (schema));
//        System.out.println("schema = " + schema);
////        try{
////            requestBody.put("schema", mapper.writeValueAsString(schema));
////        }catch (IOException e) {
////            e.printStackTrace();
////        }
//
////        System.out.println("schema = " + schema);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/vnd.schemaregistry.v1+json");
//        HttpEntity entity = new HttpEntity(requestBody, headers);
//        System.out.println("BODY = " + entity.getBody());
//        ResponseEntity<Map> responseEntity = this.restTemplate.postForEntity(this.endpoint + "/subjects/quotes/versions", entity, Map.class);
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            SchemaRegistrationResponse registrationResponse = new SchemaRegistrationResponse();
//            Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
//            System.out.println("RESPONSE BODY = " + responseBody);
//            registrationResponse.setId((Integer) responseBody.get("id"));
//            registrationResponse.setSchemaReference(new SchemaReference(subject, (Integer) responseBody.get("id"),
//                    "avro"));
//            return registrationResponse;
//        }
//        throw new RuntimeException(
//                "Failed to register schema: " + responseEntity.toString());
//    }
//
//    @SuppressWarnings("rawtypes")
//    @Override
//    public String fetch(SchemaReference schemaReference) {
//        ResponseEntity<Map> responseEntity = this.restTemplate.getForEntity(this.endpoint
//                + "/" + schemaReference.getSubject() + "/" + schemaReference.getFormat()
//                + "/v" + schemaReference.getVersion(), Map.class);
//        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
//            throw new RuntimeException("Failed to fetch schema: " + responseEntity.toString());
//        }
//        return (String) responseEntity.getBody().get("definition");
//    }
//
//    @SuppressWarnings("rawtypes")
//    @Override
//    public String fetch(int id) {
//        ResponseEntity<Map> responseEntity = this.restTemplate.getForEntity(this.endpoint + "/schemas/" + id, Map.class);
//        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
//            throw new RuntimeException("Failed to fetch schema: " + responseEntity.toString());
//        }
//        return (String) responseEntity.getBody().get("definition");
//    }
//}
