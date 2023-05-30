package com.stannis.resourceserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http.httpBasic()
            .and()
            .formLogin()

//        http.csrf().disable() //dont do that bcs our security is based on session

//        http.csrf().ignoringRequestMatchers("/smth").disable() // if you have a specific path

        http.csrf() // attach to the header the paramName and the token
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.GET, "/v1/csrf").permitAll()
                    .and()
                it.anyRequest().authenticated()
            }

        return http.build()
    }

}