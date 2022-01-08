package com.investment.security.configuration

import com.nimbusds.jose.shaded.json.JSONArray
import com.nimbusds.jose.shaded.json.JSONObject
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt

class CustomJwtGrantedAuthoritiesConverter : Converter<Jwt, Collection<GrantedAuthority>> {

    override fun convert(jwt: Jwt): Collection<GrantedAuthority>? {
        return ((jwt.getClaim<JSONObject>("realm_access"))["roles"] as JSONArray?)!!
                .map { role: Any -> "ROLE_" + role as String }
                .map { role: String? -> SimpleGrantedAuthority(role) }
    }
}
