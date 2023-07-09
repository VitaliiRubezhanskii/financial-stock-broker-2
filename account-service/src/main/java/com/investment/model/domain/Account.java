package com.investment.model.domain;

import com.investment.model.domain.enums.AccountStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    private String id;

    private String account;

    private BigDecimal balance;

    private AccountStatus status;

}
