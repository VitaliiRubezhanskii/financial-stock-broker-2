package com.investment.trading.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    private String id;
    private String sku;
}
