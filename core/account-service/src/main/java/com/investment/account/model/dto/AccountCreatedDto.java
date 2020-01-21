package com.investment.account.model.dto;

import com.investment.account.model.domain.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatedDto {

    private String id;

    private String account;

    private BigDecimal balance;

    private AccountStatus status;
}
