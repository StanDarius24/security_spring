package com.stannis.resourceserver.config

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationManagerResolver
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector
import org.springframework.security.web.SecurityFilterChain


@Configuration
class ProjectConfig {
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.oauth2ResourceServer { j: OAuth2ResourceServerConfigurer<HttpSecurity?> ->
            j.authenticationManagerResolver(
                authenticationManagerResolver(jwtDecoder(), opaqueTokenIntrospector())
            )
        }
        http.authorizeHttpRequests().anyRequest().authenticated()
        return http.build()
    }

    //  @Bean
    //  public AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver() {
    //    JwtIssuerAuthenticationManagerResolver a =
    //        new JwtIssuerAuthenticationManagerResolver("http://localhost:8081", "http://localhost:8082");
    //
    //    return a;
    //  }
    @Bean
    fun authenticationManagerResolver(
        jwtDecoder: JwtDecoder?, opaqueTokenIntrospector: OpaqueTokenIntrospector?
    ): AuthenticationManagerResolver<HttpServletRequest> {
        val jwtAuth: AuthenticationManager = ProviderManager(
            JwtAuthenticationProvider(jwtDecoder)
        )
        val opaqueAuth: AuthenticationManager = ProviderManager(
            OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector)
        )
        return AuthenticationManagerResolver { request: HttpServletRequest ->
            if ("jwt" == request.getHeader("type")) {
                return@AuthenticationManagerResolver jwtAuth
            } else {
                return@AuthenticationManagerResolver opaqueAuth
            }
        }
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder
            .withJwkSetUri("http://localhost:8081/oauth2/jwks")
            .build()
    }

    @Bean
    fun opaqueTokenIntrospector(): OpaqueTokenIntrospector {
        return SpringOpaqueTokenIntrospector(
            "http://localhost:8082/oauth2/introspect",
            "client", "secret"
        )
    }
}