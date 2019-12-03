package com.investment.authservice.repository;

import com.investment.authservice.domain.OauthClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Oauth2ClientRepository extends MongoRepository<OauthClientDetails, String> {

    Optional<OauthClientDetails> findByClientId(String clientId);
}
