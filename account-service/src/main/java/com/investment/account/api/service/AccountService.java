package com.investment.account.api.service;

import com.investment.account.model.domain.enums.AccountStatus;
import com.investment.account.model.dto.AccountCreatedDto;
import com.investment.account.model.dto.AccountCreationDto;

public interface AccountService {

    AccountCreatedDto createAccount(AccountCreationDto accountCreationDto);

    AccountCreatedDto updateAccount(String accountId, AccountCreationDto accountCreationDto);

    void lockAccount(String id, AccountStatus status);

    AccountCreatedDto getAccountById(String id);

}
