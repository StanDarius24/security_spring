package com.stannis.clientserver.config

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManagerResolver
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver
import org.springframework.security.web.SecurityFilterChain


@Configuration
class ProjectConfig {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http.oauth2ResourceServer { j: OAuth2ResourceServerConfigurer<HttpSecurity> ->
            j.authenticationManagerResolver(
                authenticationManagerResolver()
            )
        }
        return http.build()
    }

    @Bean
    fun authenticationManagerResolver(): AuthenticationManagerResolver<HttpServletRequest?>? {
        return JwtIssuerAuthenticationManagerResolver("http://localhost:8080", "http://localhost:8081")
    }

}