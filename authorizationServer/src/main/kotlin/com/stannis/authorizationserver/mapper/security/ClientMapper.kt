package com.stannis.authorizationserver.mapper.security

import com.stannis.authorizationserver.entity.Client
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient

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
            .scope(client.scope)
            .build()
    }
}