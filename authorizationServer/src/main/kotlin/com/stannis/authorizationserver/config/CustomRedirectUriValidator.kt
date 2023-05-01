package com.stannis.authorizationserver.config

import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2ErrorCodes
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationContext
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken
import java.util.function.Consumer


class CustomRedirectUriValidator: Consumer<OAuth2AuthorizationCodeRequestAuthenticationContext> {

    @Override
    override fun accept(context: OAuth2AuthorizationCodeRequestAuthenticationContext) {
        val a: OAuth2AuthorizationCodeRequestAuthenticationToken = context.getAuthentication()
        val registeredClient = context.registeredClient
        val uri = a.redirectUri

        if (!registeredClient.redirectUris.contains(uri)) {
            val error = OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST)
            throw OAuth2AuthorizationCodeRequestAuthenticationException(error, null)
        }
    }

}