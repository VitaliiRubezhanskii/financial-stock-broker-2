package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.config.ResourceServerConfig;
import com.investment.feign_hystrix.integration.domain.account.AccountCreatedDto;
import com.investment.feign_hystrix.integration.domain.account.AccountCreationDto;
import com.investment.feign_hystrix.integration.domain.enums.AccountStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account", url = "http://localhost:5000/account/", configuration = ResourceServerConfig.class)
@Service
public interface AccountServiceClient {

    @PostMapping(value = "/account")
    AccountCreatedDto createAccount(@RequestBody AccountCreationDto accountCreationDto);

    @PatchMapping(value = "/account/{accountId}")
    AccountCreatedDto updateAccount(@PathVariable(value = "accountId") String accountId, @RequestBody AccountCreationDto accountCreationDto);

    @DeleteMapping(value = "/{accountId}/status/{status}")
    void lockAccount(@PathVariable(value = "accountId") String id, @PathVariable(value = "status") AccountStatus status);

    @GetMapping(value = "/{accountId}")
    AccountCreatedDto getAccountById(@PathVariable(value = "accountId") String id);

    @GetMapping(value = "/account/name/{account}")
    AccountCreatedDto getAccountByAccount(@PathVariable String account);

}
