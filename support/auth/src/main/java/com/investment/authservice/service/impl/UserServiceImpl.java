package com.investment.authservice.service.impl;

import com.investment.authservice.domain.User;
import com.investment.authservice.domain.enums.Authorities;
import com.investment.authservice.repository.UserRepository;
import com.investment.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    @Override
    public User create(User user) {
        validateUsernameFree(user.getUsername());
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setActivated(Boolean.TRUE); // TODO send sms or email with code for activation (on AWS maybe)
        user.setAuthorities(new HashSet<>(Collections.singletonList(Authorities.ROLE_TRADER)));
        return userRepository.save(user);
    }



    private void validateUsernameFree(String username){
        Optional<User> existingUser = userRepository.findByUsername(username);
        existingUser.ifPresent((user) -> {
            throw new IllegalArgumentException("User not available - already exists");
        });
    }
}
