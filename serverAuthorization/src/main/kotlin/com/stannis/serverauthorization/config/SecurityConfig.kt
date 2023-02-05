package com.stannis.serverauthorization.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import java.security.KeyPairGenerator
import java.util.*

@Configuration
class SecurityConfig {

    @Bean
    @Throws(Exception::class)
    @Order(1)
    fun authorizationSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)

        http.getConfigurer(OAuth2AuthorizationServerConfigurer::class.java)
            .oidc(Customizer.withDefaults())

        http.exceptionHandling{
            it.authenticationEntryPoint(
                LoginUrlAuthenticationEntryPoint("/login")
            )
        }

        return http.build()
    }

    @Bean
    @Throws(Exception::class)
    @Order(2)
    fun applicationSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.formLogin()
            .and()
            .authorizeHttpRequests().anyRequest().authenticated()
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val u1 = User.withUsername("user")
            .password("password")
            .roles("read")
            .build()
        return InMemoryUserDetailsManager(u1)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        val r1 = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("client")
            .clientSecret("secret")
            .scope(OidcScopes.OPENID)
            .scope(OidcScopes.PROFILE) // need to be enabled -> oidc(Customizer.withDefaults())
            .redirectUri("https://springone.io/authorized")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .build()
        return InMemoryRegisteredClientRepository(r1)
    }

    @Bean
    fun authorizationServerSettings(): AuthorizationServerSettings {
        return AuthorizationServerSettings.builder()
            .build()
    }

    @Bean
    @Throws(Exception::class)
    fun jwkSource(): JWKSource<SecurityContext> {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        val keyPair = keyPairGenerator.generateKeyPair()

        val publicKey = keyPair.public
        val privateKey = keyPair.private

        val key = RSAKey.Builder(publicKey as java.security.interfaces.RSAPublicKey)
            .privateKey(privateKey as java.security.interfaces.RSAPrivateKey)
            .keyID(UUID.randomUUID().toString())
            .build()

        val set = JWKSet(key)
        return ImmutableJWKSet(set)
    }

}