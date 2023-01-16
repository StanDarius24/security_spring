package com.example.security.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    private fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(11)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeHttpRequests {
                requests ->
                    requests.requestMatchers("/hello")
                        .permitAll()
                        .requestMatchers("/login")
                        .permitAll()
                        .requestMatchers("/verifyRegistration")
                        .permitAll()
                        .requestMatchers("/sendVerifyToken")
                        .permitAll()
                        .requestMatchers("/api/**")
                        .authenticated()
                        .and()
                        .oauth2Login {
                            oauthLogin ->
                            oauthLogin.loginPage("/oauth2/authorization/api-client-oidc")
                        }
                        .oauth2Client(Customizer.withDefaults())
            }
        return http.build()
    }

}