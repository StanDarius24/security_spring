package com.stannis.resourceserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class ProjectConfig {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http.oauth2ResourceServer()
            .opaqueToken()
            .introspectionUri("http://localhost:8081/oauth2/introspect")
            .introspectionClientCredentials("client", "secret")

        http.authorizeHttpRequests()
            .anyRequest().authenticated()

        return http.build()
    }

}