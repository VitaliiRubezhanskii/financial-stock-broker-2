package com.investment.trading.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedDto {

    private String ticket;

    private String volume;

    private String condition;

    private String account;

    private String bid;

    private String ask;
}
