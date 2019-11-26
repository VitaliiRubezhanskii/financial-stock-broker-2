package com.investment.feign_hystrix.integration.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private List<OrderItem> items;

    private String invoice;

    private LocalDateTime createdDate;

}
