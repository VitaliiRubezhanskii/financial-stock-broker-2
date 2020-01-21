package com.investment.account.model.domain;

import com.investment.account.model.domain.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Account {

    @Id
    private String id;

    private String account;

    private BigDecimal balance;

    private AccountStatus status;

}
