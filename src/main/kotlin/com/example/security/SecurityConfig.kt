package com.example.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.httpBasic()
            .and()
            .authorizeHttpRequests()
//            .anyRequest().authenticated()
//            .anyRequest().permitAll()
//            .anyRequest().denyAll()
//            .anyRequest().hasAuthority("read")
//            .anyRequest().hasAnyAuthority("read", "write")
//            .anyRequest().hasAnyRole("ADMIN", "MANAGER")
//            .anyRequest().access("isAuthenticated() and hasAuthority('read')") // SpEL
//            .requestMatchers("/hello1").authenticated()
//            .requestMatchers("/hello2").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/**").hasAuthority("read")
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .build()
        // matcher method + authorization rule
        // 1. which matcher methods should you use and how
        // 2. how to apply different authorization rules
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val uds = InMemoryUserDetailsManager()
        val u1 = User
            .withUsername("stan")
            .password(
                passwordEncoder().encode("123456")
            )
            .authorities("read")
//            .roles("ADMIN") // equivalent with authorities("ROLE_ADMIN")
            .build()

        val u2 = User
            .withUsername("darius")
            .password(
                passwordEncoder().encode("123456")
            )
            .authorities("write")
//            .roles("MANAGER") // equivalent with authorities("ROLE_MANAGER")
            .build()

        uds.createUser(u1)
        uds.createUser(u2)

        return uds
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}