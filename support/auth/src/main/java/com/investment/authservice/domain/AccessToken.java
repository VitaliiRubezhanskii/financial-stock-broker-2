package com.investment.authservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Document(value = "mongoAccessToken")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {
//
//    @Value("${custom.oauth.tokenId}")
//    public static String TOKEN_ID;
//
//    @Value("${custom.oauth.refresh-token}")
//    public static String REFRESH_TOKEN;
//
//    @Value("${custom.oauth.authId}")
//    public static String AUTHENTICATION_ID;
//
//    @Value("${custom.oauth.clientId}")
//    public static String CLIENT_ID;
//
//    @Value("${custom.oauth.username}")
//    public static String USER_NAME;

    public static final String TOKEN_ID = "tokenId";
    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String AUTHENTICATION_ID = "authenticationId";
    public static final String CLIENT_ID = "clientId";
    public static final String USER_NAME = "username";

    @Id
    private String id;
    private String tokenId;
    private OAuth2AccessToken token;
    private String authenticationId;
    private String username;
    private String clientId;
    private OAuth2Authentication authentication;
    private String refreshToken;


}
