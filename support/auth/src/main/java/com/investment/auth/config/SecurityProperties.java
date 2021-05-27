package com.investment.auth.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties("security")
@Getter
@Setter
@RequiredArgsConstructor
public class SecurityProperties {

    private JwtProperties jwt;

    @Getter
    @Setter
    static class JwtProperties {
        private Resource keyStore;
        private String keyStorePassword;
        private String keyPairAlias;
        private String keyPairPassword;
    }


}
