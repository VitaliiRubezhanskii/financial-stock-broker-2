package com.investment.account.api;

import com.investment.account.api.service.KeyCloakUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KeyCloakUserController {

    private final KeyCloakUserService keyCloakUserService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/user/create")
    public ResponseEntity<Void> create() {
        keyCloakUserService.create();
        return ResponseEntity.ok().build();
    }

}
