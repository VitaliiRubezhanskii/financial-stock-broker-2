package com.investment.authservice.service;

import com.investment.authservice.repository.Oauth2ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Oauth2ClientDetailsService implements ClientDetailsService {

    private final Oauth2ClientRepository oauth2ClientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return oauth2ClientRepository.findByClientId(clientId).orElseThrow(IllegalArgumentException::new); // TODO  customize exception
    }
}
