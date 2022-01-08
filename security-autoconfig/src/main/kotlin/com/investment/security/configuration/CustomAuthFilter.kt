package com.investment.security.configuration

import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthFilter(val authenticationManager: AuthenticationManager): OncePerRequestFilter() {

        @Throws(ServletException::class, IOException::class)
        override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse?, filterChain: FilterChain) {
            val header = request.getHeader("Authorization")
            if (header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response)
                return
            }

            val tokenString = (JWTParser.parse(header.substring(7)) as SignedJWT).parsedString

            val authenticationToken = BearerTokenAuthenticationToken(tokenString)
            val authentication = authenticationManager.authenticate(authenticationToken)

            val context = SecurityContextHolder.createEmptyContext()
            context.authentication = authentication
            SecurityContextHolder.setContext(context)

            filterChain.doFilter(request, response)
        }
    }
