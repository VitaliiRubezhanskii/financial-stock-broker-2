package com.investment.account.api;

import com.investment.account.model.domain.enums.AccountStatus;
import com.investment.account.model.dto.AccountCreatedDto;
import com.investment.account.model.dto.AccountCreationDto;
import com.investment.account.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountCreatedDto> newAccount(@RequestBody AccountCreationDto accountCreationDto){
        return new ResponseEntity<>(accountService.createAccount(accountCreationDto), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{accountId}")
    public ResponseEntity<AccountCreatedDto> updateAccount(@PathVariable("accountId") String accountId, @RequestBody AccountCreationDto accountCreationDto){
        return new ResponseEntity<>(accountService.updateAccount(accountId, accountCreationDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountCreatedDto> getAccountById(@PathVariable("accountId") String accountId){
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{accountId}/status/{status}")
    public ResponseEntity lockAccount(@PathVariable("accountId") String accountId, @PathVariable("status") AccountStatus status){
        accountService.lockAccount(accountId, status);
        return ResponseEntity.noContent().build();
    }
}
