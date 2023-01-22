package com.stannis.backendauth.config

import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import java.util.UUID


@EnableWebSecurity
class SecurityConfig {

    @Bean
    @Order(1)
    @Throws(
        Exception::class
    )
    fun authorizationServerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)
        http.exceptionHandling { exceptions ->
            exceptions.authenticationEntryPoint(
                LoginUrlAuthenticationEntryPoint("/login")
            )
        }
        return http.build()
    }

    @Bean
    @Order(2)
    @Throws(java.lang.Exception::class)
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorize ->
            authorize.anyRequest()
                .authenticated()
        }.formLogin(Customizer.withDefaults())
        return http.build()
    }

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        val registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("messages-client")
            .clientSecret("\$2y\$12\$7klIYWXJUbqwkpJ5VyFV..ioV7Jh9L44/tHlDIUIs/tMo3YIqhtTu")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri("http://backend-client:8083/login/oauth2/code/messages-client-oidc")
            .redirectUri("http://backend-client:8083/authorized")
            .scope(OidcScopes.OPENID)
            .scope("message.read")
            .scope("message.write")
            .build()
        return InMemoryRegisteredClientRepository(registeredClient)
    }

    @Bean
    fun providerSettings(): ProviderSettings {
        return ProviderSettings.builder()
            .issuer("http://backend-auth:8081")
            .build()
    }

}