package com.investment.authservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Document(value = "mongoRefreshToken")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {


    @Value("${custom.oauth.tokenId}")
    public static String TOKEN_ID;

    @Id
    private String id;
    private String tokenId;
    private OAuth2RefreshToken token;
    private OAuth2Authentication authentication;
}
