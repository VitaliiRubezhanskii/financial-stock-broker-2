package com.investment.feign_hystrix.integration.domain;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private String sku;
    private String port;
}
