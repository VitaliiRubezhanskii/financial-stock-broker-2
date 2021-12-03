package com.investment.security.configuration

import feign.RequestInterceptor
import feign.RequestTemplate
import org.bouncycastle.util.Arrays
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.MethodInvokingFactoryBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import javax.servlet.GenericFilter

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@ConditionalOnProperty(value = ["spring.cloud.config.enabled"], havingValue = "true", matchIfMissing = true)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.anonymous().disable()
        httpSecurity
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .oauth2ResourceServer { oauth2ResourceServer ->
                    oauth2ResourceServer
                            .jwt { jwt -> jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter()) }
                }
    }

    private fun getJwtAuthenticationConverter(): Converter<Jwt, AbstractAuthenticationToken>{
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(CustomJwtGrantedAuthoritiesConverter())
        return jwtAuthenticationConverter
    }

    @Bean
    fun jwtDecoder(): JwtDecoder? {
        println("Hello from SECURITY AUTOCONFIG ENABLED!!!!!")
        val timestampValidator = JwtTimestampValidator()
        val issuerValidator = JwtIssuerValidator("http://localhost:8080/auth/realms/broker")
        val validator: OAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(
                timestampValidator,
                issuerValidator)
        val decoder = NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/auth/realms/broker/protocol/openid-connect/certs")
                .build()
        decoder.setJwtValidator(validator)
        return decoder
    }

    @Bean
    fun oauth2FeignRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            val token: String = (SecurityContextHolder.getContext().authentication as JwtAuthenticationToken).token.tokenValue
            val bearerString = String.format("%s %s", "Bearer", token)
            requestTemplate.header(HttpHeaders.AUTHORIZATION, listOf(bearerString))
        }
    }

    @Bean
    fun filterRegistrationBean(): FilterRegistrationBean<CustomAuthFilter> {
        val registrationBean: FilterRegistrationBean<CustomAuthFilter> = FilterRegistrationBean<CustomAuthFilter>()
        registrationBean.filter = CustomAuthFilter(authenticationManagerBean())
        registrationBean.order = Int.MAX_VALUE
        return registrationBean
    }

    @Bean
    fun methodInvokingFactoryBean(): MethodInvokingFactoryBean {
        val methodInvokingFactoryBean = MethodInvokingFactoryBean();
        methodInvokingFactoryBean.targetClass = SecurityContextHolder::class.java
        methodInvokingFactoryBean.targetMethod = "setStrategyName"
        methodInvokingFactoryBean.setArguments(arrayOf(MODE_INHERITABLETHREADLOCAL))
        return methodInvokingFactoryBean;
    }
}