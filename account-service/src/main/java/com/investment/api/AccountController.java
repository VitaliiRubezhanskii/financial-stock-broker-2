package com.investment.api;

import com.investment.model.domain.enums.AccountStatus;
import com.investment.model.dto.AccountCreatedDto;
import com.investment.model.dto.AccountCreationDto;
import com.investment.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountCreatedDto> newAccount(@RequestBody AccountCreationDto accountCreationDto){
        return new ResponseEntity<>(accountService.createAccount(accountCreationDto), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{accountId}")
    public ResponseEntity<AccountCreatedDto> updateAccount(@PathVariable("accountId") String accountId, @RequestBody AccountCreationDto accountCreationDto){
        AccountCreatedDto accountCreatedDto = new AccountCreatedDto();
        accountCreatedDto.setId("2132wqqwqw2332");
        accountCreatedDto.setAccount("acc-service-acc");
        return new ResponseEntity<>(accountCreatedDto, HttpStatus.ACCEPTED);
//        return new ResponseEntity<>(accountService.updateAccount(accountId, accountCreationDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('default-roles-my-realm')")
    @GetMapping("/")
    public ResponseEntity<AccountCreatedDto> getAccountById(){
        AccountCreatedDto accountCreatedDto = new AccountCreatedDto();
        accountCreatedDto.setId("2132wqqwqw2332");
        accountCreatedDto.setAccount("acc-service-acc");
        return new ResponseEntity<>(accountCreatedDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{accountId}/status/{status}")
    public ResponseEntity lockAccount(@PathVariable("accountId") String accountId, @PathVariable("status") AccountStatus status){
        accountService.lockAccount(accountId, status);
        return ResponseEntity.noContent().build();
    }
}
