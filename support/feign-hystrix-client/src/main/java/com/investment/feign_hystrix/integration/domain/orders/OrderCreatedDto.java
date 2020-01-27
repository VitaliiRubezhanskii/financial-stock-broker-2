package com.investment.feign_hystrix.integration.domain.orders;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedDto {

    private String id;

    private String ticket;

    private String volume;

    private String condition;

    private String account;

    private String bid;

    private String ask;
}
