package com.investment.trading.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

//    @Id
//    private String id;
    private List<OrderItem> items;
    private String invoice;
    @CreatedDate
    private LocalDateTime createdDate;
}
