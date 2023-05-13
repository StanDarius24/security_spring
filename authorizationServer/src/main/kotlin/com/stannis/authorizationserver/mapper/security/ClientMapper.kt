package com.stannis.authorizationserver.mapper.security

import com.stannis.authorizationserver.entity.Client
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings
import java.time.Duration

object ClientMapper {

    fun from(registeredClient: RegisteredClient): Client {
        return Client(
            id = null,
            clientId = registeredClient.clientId,
            secret = registeredClient.clientSecret!!,
            redirectUri = registeredClient.redirectUris.first(), // not good
            authMethod = registeredClient.clientAuthenticationMethods.first().value, // not good
            grantType = registeredClient.authorizationGrantTypes.first().value, // not good
            scope = registeredClient.scopes.first() // not good
        )
    }

    fun to(client: Client): RegisteredClient {
        return RegisteredClient.withId(client.id.toString())
            .clientId(client.clientId)
            .clientSecret(client.secret)
            .redirectUri(client.redirectUri)
            .authorizationGrantType(AuthorizationGrantType(client.grantType))
            .clientAuthenticationMethod(ClientAuthenticationMethod(client.authMethod))
            .tokenSettings( // opaque -> you cant see the information in this token
                TokenSettings.builder()
                    .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                    .accessTokenTimeToLive(Duration.ofDays(1))
                    .build()
            )
            .scope(client.scope)
            .build()
    }
}