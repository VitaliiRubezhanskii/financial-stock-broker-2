package com.investment.security.configuration

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Slf4j
@RequiredArgsConstructor
class KeycloakLogoutHandler(val restTemplate: RestTemplate): SecurityContextLogoutHandler() {

    override fun logout(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication) {
        super.logout(request, response, authentication)
        propagateLogoutToKeycloak(authentication.principal as OidcUser)
    }

    private fun propagateLogoutToKeycloak(user: OidcUser) {
        val endSessionEndpoint = user.issuer.toString() + "/protocol/openid-connect/logout"
        val builder = UriComponentsBuilder //
            .fromUriString(endSessionEndpoint) //
            .queryParam("id_token_hint", user.idToken.tokenValue)
        val logoutResponse = restTemplate.getForEntity(
            builder.toUriString(),
            String::class.java
        )
        if (logoutResponse.statusCode.is2xxSuccessful) {
           logger.info("Successfulley logged out in Keycloak")
        } else {
           logger.info("Could not propagate logout to Keycloak")
        }
    }
}