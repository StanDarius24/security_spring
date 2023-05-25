package com.stannis.resourceserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
class SecurityConfig {


    @Bean
    fun securityFilter(http: HttpSecurity): SecurityFilterChain {

        http.authorizeHttpRequests()
                .anyRequest()
                .permitAll()

        http.cors{
            it.configurationSource {
                val configuration = CorsConfiguration()
                configuration.allowedOrigins = listOf("*") // add here links (in app.properties) never *
                return@configurationSource configuration
            }
        }


        return http.build()
    }

}