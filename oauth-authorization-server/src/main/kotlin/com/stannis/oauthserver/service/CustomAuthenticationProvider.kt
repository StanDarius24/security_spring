package com.stannis.oauthserver.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomAuthenticationProvider : AuthenticationProvider {

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun authenticate(authentication: Authentication): Authentication {
        return checkPassword(
            customUserDetailsService.loadUserByUsername(authentication.name),
            authentication.credentials.toString()
        )
    }

    private fun checkPassword(user: UserDetails, rawPassword: String): Authentication {
        if (passwordEncoder.matches(rawPassword, user.password)) {
            return UsernamePasswordAuthenticationToken(
                user.username,
                user.password,
                user.authorities
            )
        } else {
            throw BadCredentialsException("Bad credentials")
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}