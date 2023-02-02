package com.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
// @PreAuthorize @PostAuthorize @PreFilter @PostFilter

/*
    @Secured,
    @RolesAllowed
 */

class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.httpBasic()
            .and()
            .authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and()
            .build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val u1 = User.withUsername("john")
            .password(passwordEncoder().encode("12345"))
            .authorities("read")
            .build()
        val u2 = User.withUsername("bill")
            .password(passwordEncoder().encode("12345"))
            .authorities("write")
            .build()
        val mudm = InMemoryUserDetailsManager()
        mudm.createUser(u1)
        mudm.createUser(u2)
        return mudm
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}