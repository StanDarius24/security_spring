package com.stannis.resourceserver.config

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManagerResolver
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.oauth2ResourceServer{
            it.authenticationManagerResolver(authenticationManagerResolver()) // can manage multiple application providers
        }

        return http.build()
    }

    @Bean
    fun authenticationManagerResolver(): AuthenticationManagerResolver<HttpServletRequest> {
        val a = JwtIssuerAuthenticationManagerResolver("http://localhost:8080", "http://localhost:8081")

        return a
    }

//    @Bean
//    fun authManagerResolver(): AuthenticationManagerResolver<HttpServletRequest> {
//        val a = ProviderManager(JwtAuthenticationProvider(JwtDecoder))
//
//        return a -> r
//    }

}