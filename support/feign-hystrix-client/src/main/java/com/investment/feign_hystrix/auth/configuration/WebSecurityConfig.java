package com.investment.feign_hystrix.auth.configuration;

import com.investment.feign_hystrix.auth.service.AuthenticationManager;
import com.investment.feign_hystrix.auth.service.SecurityContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.client.ExchangeStrategies.withDefaults;


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final AuthenticationManager authenticationManager;

	private final SecurityContextRepository securityContextRepository;
//
	@Bean
	public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
		return http
				.exceptionHandling()
				.authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
						.accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
				.and()
				.csrf().disable()
				.formLogin().disable()
				.httpBasic().disable()
				.authenticationManager(authenticationManager)
				.securityContextRepository(securityContextRepository)
				.authorizeExchange()
				.pathMatchers(HttpMethod.OPTIONS).permitAll()
				.pathMatchers("/login").permitAll()
				.anyExchange().authenticated()
				.and().build();
	}


	@Bean
	public ReactiveUserDetailsService findByUsername(ReactiveUserDetailsService reactiveUserDetailsService) {
		return  username -> reactiveUserDetailsService.findByUsername(username)
				.switchIfEmpty(Mono.defer(() ->  Mono.error(new UsernameNotFoundException("User Not Found"))))
				.cast(UserDetails.class);

	}
}
