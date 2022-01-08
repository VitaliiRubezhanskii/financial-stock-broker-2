package com.investment.trading.model.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String _id;

    private String ticket;

    private String volume;

    private String condition;

    private String account;

    private String bid;

    private String ask;


}
