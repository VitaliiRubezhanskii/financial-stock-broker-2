package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.account.AccountCreatedDto;
import com.investment.feign_hystrix.integration.domain.account.AccountCreationDto;
import com.investment.feign_hystrix.integration.domain.enums.AccountStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "account")
@Service
public interface AccountServiceClient {

    @PostMapping(value = "/account")
    AccountCreatedDto createAccount(AccountCreationDto accountCreationDto);

    @PatchMapping(value = "/account/{accountId}")
    AccountCreatedDto updateAccount(String accountId, AccountCreationDto accountCreationDto);

    @DeleteMapping(value = "/{accountId}/status/{status}")
    void lockAccount(String id, AccountStatus status);

    @GetMapping(value = "/{accountId}")
    AccountCreatedDto getAccountById(String id);

}
