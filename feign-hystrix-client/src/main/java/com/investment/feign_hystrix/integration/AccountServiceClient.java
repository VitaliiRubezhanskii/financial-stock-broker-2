package com.investment.feign_hystrix.integration;

//import com.investment.feign_hystrix.config.ResourceServerConfig;
import com.investment.feign_hystrix.integration.domain.account.AccountCreatedDto;
import com.investment.feign_hystrix.integration.domain.account.AccountCreationDto;
import com.investment.feign_hystrix.integration.domain.enums.AccountStatus;
import com.investment.security.configuration.SecurityConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account"
        , url = "http://localhost:8093/",
        configuration = SecurityConfiguration.class
)
@Service
public interface AccountServiceClient {

    @PostMapping(value = "/account")
    @ResponseBody
    AccountCreatedDto createAccount(@RequestBody AccountCreationDto accountCreationDto);

    @PatchMapping(value = "/account/{accountId}")
    @ResponseBody
    AccountCreatedDto updateAccount(@PathVariable(value = "accountId") String accountId, @RequestBody AccountCreationDto accountCreationDto);

    @DeleteMapping(value = "/{accountId}/status/{status}")
    void lockAccount(@PathVariable(value = "accountId") String id, @PathVariable(value = "status") AccountStatus status);

    @GetMapping(value = "/{accountId}")
    @ResponseBody
    AccountCreatedDto getAccountById(@PathVariable(value = "accountId") String id);

    @GetMapping(value = "/account/name/{account}")
    @ResponseBody
    AccountCreatedDto getAccountByAccount(@PathVariable(value = "account") String account);

}
