package com.investment.auth.config;

import com.investment.auth.api.service.UserService;
import com.investment.auth.domain.User;
import com.investment.auth.enums.SimpleAuthority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final SecurityProperties securityProperties;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final UsernamePasswordAuthenticationToken authentication = getAuthentication(header);

        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private KeyPair keyPair(SecurityProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
        return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias(), jwtProperties.getKeyPairPassword().toCharArray());
    }

    private KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.JwtProperties jwtProperties) {
        return new KeyStoreKeyFactory(jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        try {
            SecurityProperties.JwtProperties jwtProperties = securityProperties.getJwt();
            KeyPair keyPair = keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));
            Jws<Claims> parsedToken = Jwts.parser()
                    .setSigningKey(keyPair.getPublic())
                    .parseClaimsJws(header.replace("Bearer ", ""));
            Claims claims = parsedToken.getBody();
            String username = (String) claims.get("user_name");
            User user = userService.findUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        }
        catch (UsernameNotFoundException exception){
            log.error("User not found by username");
        }
        catch (UnsupportedJwtException exception) {
            log.warn("Request to parse unsupported JWT : {} failed : {}", header, exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.warn("Request to parse invalid JWT : {} failed : {}", header, exception.getMessage());
        } catch (SignatureException exception) {
            log.warn("Request to parse JWT with invalid signature : {} failed : {}", header, exception.getMessage());
        } catch (IllegalArgumentException exception) {
        log.warn("Request to parse empty or null JWT : {} failed : {}", header, exception.getMessage());
    }
        return null;
}
    private Set<GrantedAuthority> getAuthorities(Set<SimpleAuthority> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString())).collect(Collectors.toSet());
    }
}
