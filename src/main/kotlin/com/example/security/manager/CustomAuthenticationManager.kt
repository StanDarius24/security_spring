package com.example.security.manager

import com.example.security.providers.ApiKeyProvider
import lombok.AllArgsConstructor
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication

@AllArgsConstructor
class CustomAuthenticationManager(key: String): AuthenticationManager {

    private val key: String

    init {
        this.key = key
    }

    override fun authenticate(authentication: Authentication): Authentication {
        val provider = ApiKeyProvider(key)
        if (provider.supports(authentication.javaClass))
            return provider.authenticate(authentication)
        else
            throw IllegalArgumentException("Invalid authentication")
    }

}