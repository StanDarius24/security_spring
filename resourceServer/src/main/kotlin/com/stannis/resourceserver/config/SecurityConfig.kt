package com.stannis.resourceserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .anyRequest()
            .permitAll()

//        http.csrf().disable() //dont do that bcs our security is based on session

//        http.csrf().ignoringRequestMatchers("/smth").disable() // if you have a specific path

        http.csrf() // attach to the header the paramName and the token

        return http.build()
    }

}