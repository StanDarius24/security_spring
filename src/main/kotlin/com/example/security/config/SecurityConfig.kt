package com.example.security.config

import com.example.security.filters.ApiKeyFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class SecurityConfig {

    @Value("\${secret.key}")
    private lateinit var key :String

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.httpBasic()
            .and()
            .addFilterBefore(ApiKeyFilter(key), BasicAuthenticationFilter::class.java)
            .authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and()
            .build()
    }

}