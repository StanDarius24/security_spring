package com.example.security.config.security.manager

import com.example.security.config.security.providers.CustomAuthenticationProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationManager(provider: CustomAuthenticationProvider): AuthenticationManager {

    private val provider: CustomAuthenticationProvider

    init {
        this.provider = provider
    }

    override fun authenticate(authentication: Authentication): Authentication? {
        if (provider.supports(authentication.javaClass)) {
            return provider.authenticate(authentication)
        }

        throw BadCredentialsException("oh no!")
    }

}