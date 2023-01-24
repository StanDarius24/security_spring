package com.example.security.config.security.providers

import com.example.security.config.security.authentication.CustomAuthentication
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider: AuthenticationProvider {

    @Value("\${secret.key}")
    private lateinit var key :String

    override fun authenticate(authentication: Authentication): Authentication? {
        val ca = authentication as CustomAuthentication

        val headerKey = ca.key

        if (key == headerKey) {
            return CustomAuthentication(true, null)
        }
        throw BadCredentialsException("oh no!")
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return CustomAuthentication::class.java == authentication
    }

}