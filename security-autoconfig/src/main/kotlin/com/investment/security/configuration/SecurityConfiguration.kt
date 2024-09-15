package com.investment.security.configuration

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.client.RestTemplate

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
class SecurityConfiguration(private val oAuth2ClientProperties: OAuth2ClientProperties) {

    @Throws(Exception::class)
    @Bean
    fun securityFilterChain(
        httpSecurity: HttpSecurity,
        keycloakOauth2UserService: KeycloakOauth2UserService,
        keycloakLogoutHandler: KeycloakLogoutHandler,

    ): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests { customizer ->
                customizer
                    .requestMatchers("/actuator/**").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement { customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .logout { logoutCustomizer -> logoutCustomizer.addLogoutHandler(keycloakLogoutHandler) }
            .oauth2Login { oauth2LoginCustomizer ->
                oauth2LoginCustomizer.userInfoEndpoint { userIECustomizer ->
                    userIECustomizer.oidcUserService(
                        keycloakOauth2UserService
                    )
                }
            }

            .oauth2ResourceServer { c ->
                c.jwt { jwt -> jwt
                    .decoder(NimbusJwtDecoder.withJwkSetUri(oAuth2ClientProperties.provider["my-keycloak-provider"]?.jwkSetUri).build())
                    .jwtAuthenticationConverter(jwtAuthenticationConverter())
                }
            }
            .csrf { customizer -> customizer.disable() }
            .httpBasic { httpBasicCustomizer -> httpBasicCustomizer.disable() }
            .anonymous { c -> c.disable() }
            .build()
    }

    @Bean
    fun keycloakLogoutHandler(): KeycloakLogoutHandler {
        return KeycloakLogoutHandler(RestTemplate())
    }

    @Bean
    fun keycloakOidcUserService(): KeycloakOauth2UserService? {
        val jwtDecoder = NimbusJwtDecoder.withJwkSetUri(oAuth2ClientProperties.provider["my-keycloak-provider"]?.jwkSetUri).build()
        val authoritiesMapper = SimpleAuthorityMapper()
        authoritiesMapper.setConvertToUpperCase(true)
        return KeycloakOauth2UserService(jwtDecoder, authoritiesMapper)
    }

    private fun jwtAuthenticationConverter(): Converter<Jwt?, out AbstractAuthenticationToken?>? {
        val jwtConverter = JwtAuthenticationConverter()
        jwtConverter.setJwtGrantedAuthoritiesConverter(RealmRoleConverter())
        return jwtConverter
    }

    @Bean
    fun oauth2FeignRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            val token: String =
                (SecurityContextHolder.getContext().authentication as JwtAuthenticationToken).token.tokenValue
            val bearerString = String.format("%s %s", "Bearer", token)
            requestTemplate.header(HttpHeaders.AUTHORIZATION, listOf(bearerString))
        }
    }
}