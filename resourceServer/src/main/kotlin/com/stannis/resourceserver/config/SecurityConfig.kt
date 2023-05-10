package com.stannis.resourceserver.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {

    @Value("\${jwksUri}")
    private val jwksUri: String? = null

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.oauth2ResourceServer { r ->
            r.jwt().jwkSetUri(jwksUri)
                .jwtAuthenticationConverter(CustomJwtAuthenticationTokenConverter())
        }
        http.authorizeHttpRequests()
            .anyRequest()
            .authenticated()
        return http.build()
    }

}