package com.investment.feign_hystrix.reactive.security;

import com.investment.feign_hystrix.reactive.model.User;
import com.investment.feign_hystrix.reactive.security.model.LoginRequest;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class AuthHandler {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;

    public Mono login(ServerRequest request) {
        Mono<LoginRequest> loginRequest = request.bodyToMono(LoginRequest.class);
        ApiResponse badResponse = new ApiResponse();
        badResponse.setDescription("User does not exist");
        return loginRequest.flatMap(login -> userRepository.findByUsername(login.getUsername())
                .flatMap(user -> {
                    if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                        return ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(new LoginResponse(jwtUtil.generateToken(user))));
                    } else {
                        return ServerResponse.badRequest().body(BodyInserters.fromObject(badResponse));
                    }
                }).switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromObject(badResponse))));
    }

    public Mono signUp(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        ApiResponse badResponse = new ApiResponse();
        badResponse.setDescription("User does not exist");
        return userMono.map(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return user;
        }).flatMap(user -> userRepository.findByUsername(user.getUsername())
                .flatMap(dbUser -> ServerResponse.badRequest().body(BodyInserters.fromObject(badResponse)))
                .switchIfEmpty(userRepository.save(user).flatMap(savedUser -> ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(savedUser)))));
    }


}
