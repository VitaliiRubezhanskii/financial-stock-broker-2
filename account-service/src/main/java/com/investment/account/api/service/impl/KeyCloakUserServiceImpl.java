package com.investment.account.api.service.impl;

import com.investment.account.api.service.KeyCloakUserService;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;

@Service
public class KeyCloakUserServiceImpl implements KeyCloakUserService {

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Override
    public void create() {
        Keycloak keycloak = KeycloakBuilder.builder() //
                .serverUrl("http://localhost:8080/auth") //
                .realm("broker") //
                .grantType(OAuth2Constants.PASSWORD) //
                .clientId("broker") //
                .clientSecret("89623893-4aab-4ae3-a646-411193462f8a") //
                .username("vitalii.rubezhanskii@gmail.com") //
                .password("qwerty") //
                .build();
        // Define user
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername("vitalii.sergeevich");
        user.setFirstName("Vitalii");
        user.setLastName("Sergeevich");
        user.setEmail("vitaliy.rubezhanskii@gmail.com");
        user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

        // Get realm
        RealmResource realmResource = keycloak.realm("broker");
        UsersResource usersResource = realmResource.users();
        Response response = usersResource.create(user);

        System.out.printf("Repsonse: %s ", response.getStatus());
        String userId = CreatedResponseUtil.getCreatedId(response);


        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue("qwerty");

        UserResource userResource = usersResource.get(userId);

        // Set password credential
        userResource.resetPassword(passwordCred);

//        // Get realm role "tester" (requires view-realm role)

        RoleRepresentation testerRealmRole = realmResource.roles()//
                .get("manage-users").toRepresentation();
//
//        // Assign realm role tester to user
        userResource.roles().realmLevel() //
                .add(Arrays.asList(testerRealmRole));
//
//        // Get client
        ClientRepresentation app1Client = realmResource.clients() //
                .findByClientId("broker").get(0);
//
//        // Get client level role (requires view-clients role)
        RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId()) //
                .roles().get("manage-users").toRepresentation();
//
//        // Assign client level role to user
        userResource.roles() //
                .clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));
    }
}
