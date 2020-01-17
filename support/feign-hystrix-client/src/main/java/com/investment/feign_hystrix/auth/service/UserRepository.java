package com.investment.feign_hystrix.auth.service;

import com.investment.feign_hystrix.auth.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String > {

    Mono<User> findByUsername(String username);
}
