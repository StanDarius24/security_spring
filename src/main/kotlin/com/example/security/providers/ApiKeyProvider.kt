package com.example.security.providers

import com.example.security.authentications.ApiKeyAuthentication
import lombok.AllArgsConstructor
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication

@AllArgsConstructor
class ApiKeyProvider(key: String): AuthenticationProvider {

    private val key: String

    init {
        this.key = key
    }

    override fun authenticate(authentication: Authentication?): Authentication {
        authentication?.let {
            val auth = (it as ApiKeyAuthentication)
            if (auth.getKey() == key)
                auth.isAuthenticated = true
            return auth
        }
        throw BadCredentialsException("Invalid API key")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return ApiKeyAuthentication::class.java.isAssignableFrom(authentication)
    }
}