package com.investment.account.api;

import com.investment.account.model.domain.enums.AccountStatus;
import com.investment.account.model.dto.AccountCreatedDto;
import com.investment.account.api.service.AccountService;
import com.investment.account.model.dto.AccountCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping(value = "/account")
@RequiredArgsConstructor
public class AccountController {

//    private final AccountService accountService;

//    @PostMapping
//    public ResponseEntity<AccountCreatedDto> newAccount(@RequestBody AccountCreationDto accountCreationDto){
//        return new ResponseEntity<>(accountService.createAccount(accountCreationDto), HttpStatus.CREATED);
//    }
//
//    @PatchMapping(value = "/{accountId}")
//    public ResponseEntity<AccountCreatedDto> updateAccount(@PathVariable("accountId") String accountId, @RequestBody AccountCreationDto accountCreationDto){
//        return new ResponseEntity<>(accountService.updateAccount(accountId, accountCreationDto), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/{accountId}")
//    public ResponseEntity<AccountCreatedDto> getAccountById(@PathVariable("accountId") String accountId){
//        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
//    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/name/{account}")
    public ResponseEntity<AccountCreatedDto> getAccountByAccount(@PathVariable("account") String accountId,
                                                                 HttpServletRequest request) throws InterruptedException{
        Principal principal = request.getUserPrincipal();
        Thread.sleep(5000L);
//        return new ResponseEntity<>(accountService.getAccountByAccount(accountId), HttpStatus.OK);
        return new ResponseEntity<>(new AccountCreatedDto("1", accountId, new BigDecimal("50.05"), AccountStatus.ACTIVE), HttpStatus.ACCEPTED);
    }

//    @DeleteMapping(value = "/{accountId}/status/{status}")
//    public ResponseEntity lockAccount(@PathVariable("accountId") String accountId, @PathVariable("status") AccountStatus status){
//        accountService.lockAccount(accountId, status);
//        return ResponseEntity.noContent().build();
//    }
}
