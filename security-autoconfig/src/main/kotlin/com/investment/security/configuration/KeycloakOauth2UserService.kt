package com.investment.security.configuration

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap
import lombok.RequiredArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2ErrorCodes
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtException

@RequiredArgsConstructor
class KeycloakOauth2UserService(
    private val jwtDecoder: JwtDecoder,
    private val authoritiesMapper: SimpleAuthorityMapper): OidcUserService() {

    @Throws(OAuth2AuthenticationException::class)
    override fun loadUser(userRequest: OidcUserRequest): OidcUser? {
        val user = super.loadUser(userRequest)
        val authorities: MutableSet<GrantedAuthority> = LinkedHashSet()
        authorities.addAll(user.authorities)
        authorities.addAll(extractKeycloakAuthorities(userRequest))
        return DefaultOidcUser(authorities, userRequest.idToken, user.userInfo, "preferred_username")
    }
    private fun extractKeycloakAuthorities(userRequest: OidcUserRequest): MutableSet<GrantedAuthority> {
        val authorities =  ((((parseJwt(userRequest.accessToken.tokenValue).getClaim<LinkedTreeMap<*, *>>("resource_access"))?.get("account") as LinkedTreeMap<*, *>)["roles"]) as ArrayList<*>)
            .map { role: Any -> role as String }
            .map { role: String? -> SimpleGrantedAuthority(role) }
        return authoritiesMapper.mapAuthorities(authorities)
    }

    private fun parseJwt(accessTokenValue: String): Jwt {
        return try {
            jwtDecoder.decode(accessTokenValue)
        } catch (e: JwtException) {
            throw OAuth2AuthenticationException(OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST), e)
        }
    }
}