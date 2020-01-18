package com.investment.feign_hystrix.auth.service;


import com.investment.feign_hystrix.auth.model.Role;
import com.investment.feign_hystrix.auth.util.TokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

	private final TokenProvider tokenProvider;


	@Override
	@SuppressWarnings("unchecked")
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();
		
		String username;
		try {
			username = tokenProvider.getUsernameFromToken(authToken);
		} catch (Exception e) {
			username = null;
		}
		if (username != null && tokenProvider.validateToken(authToken)) {
			Claims claims = tokenProvider.getAllClaimsFromToken(authToken);
			List<String> rolesMap = claims.get("role", List.class);
			List<Role> roles = new ArrayList<>();
			for (String rolemap : rolesMap) {
				roles.add(Role.valueOf(rolemap));
			}
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				username,
				null,
				roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList())
			);
			return Mono.just(auth);
		} else {
			return Mono.empty();
		}
	}



}
