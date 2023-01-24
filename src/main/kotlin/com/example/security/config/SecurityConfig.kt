package com.example.security.config

import com.example.security.config.security.filters.CustomAuthenticationFilter
import lombok.AllArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@AllArgsConstructor
class SecurityConfig(customAuthenticationFilter: CustomAuthenticationFilter) {

    private final var customAuthenticationFilter: CustomAuthenticationFilter

    init {
        this.customAuthenticationFilter = customAuthenticationFilter
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        return http.addFilterAt(
            customAuthenticationFilter,
            UsernamePasswordAuthenticationFilter::class.java
        ) // add a filter in UsernamePasswordAuthenticationFilter (customAuthenticationFilter)
            .authorizeHttpRequests().anyRequest().authenticated() // ignore this for now
            .and()
            .build()

    }

}