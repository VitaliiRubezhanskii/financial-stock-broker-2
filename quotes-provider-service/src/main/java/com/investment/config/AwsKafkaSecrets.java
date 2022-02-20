package com.investment.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AwsKafkaSecrets {

    private String username;
    private String password;
}
