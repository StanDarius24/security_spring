package com.stannis.oauthserver.config

import com.stannis.oauthserver.service.CustomAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component

@EnableWebSecurity
@Component
class DefaultSecurityConfig {

    @Autowired
    private lateinit var customAuthenticationProvider: CustomAuthenticationProvider

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorizeRequests -> authorizeRequests.anyRequest().authenticated() }
            .formLogin(Customizer.withDefaults())
        return http.build()
    }

    @Autowired
    fun bindAuthenticationProvider(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
            .authenticationProvider(customAuthenticationProvider)
    }

}