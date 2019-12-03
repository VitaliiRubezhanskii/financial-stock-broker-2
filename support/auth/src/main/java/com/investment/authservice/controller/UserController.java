package com.investment.authservice.controller;

import com.investment.authservice.domain.User;
import com.investment.authservice.service.UserService;
import com.investment.authservice.transfer.UserDTO;
import com.investment.authservice.transfer.UserRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PostMapping
    @PreAuthorize("#oauth2.hasScope('server')")
    public UserDTO createUser(@Valid @RequestBody UserRegistrationDTO userRegistration) {
        User savedUser = userService.create(toUser(userRegistration));
        return toDto(savedUser);
    }

    private UserDTO toDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private User toUser(UserRegistrationDTO userRegistration) {
        User user = new User();
        user.setUsername(userRegistration.getUsername());
        user.setPassword(userRegistration.getPassword());
        return user;
    }

}
