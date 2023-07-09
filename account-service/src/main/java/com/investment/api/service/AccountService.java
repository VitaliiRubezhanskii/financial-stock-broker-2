package com.investment.api.service;

import com.investment.model.domain.enums.AccountStatus;
import com.investment.model.dto.AccountCreatedDto;
import com.investment.model.dto.AccountCreationDto;

public interface AccountService {

    AccountCreatedDto createAccount(AccountCreationDto accountCreationDto);

    AccountCreatedDto updateAccount(String accountId, AccountCreationDto accountCreationDto);

    void lockAccount(String id, AccountStatus status);

    AccountCreatedDto getAccountById(String id);

}
