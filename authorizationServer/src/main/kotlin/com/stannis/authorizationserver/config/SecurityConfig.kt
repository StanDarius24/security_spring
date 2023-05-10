package com.stannis.authorizationserver.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import java.util.function.Consumer


@Configuration
class SecurityConfig {

    // check this -> http://localhost:8081/oauth2/jwks -> it won't work if you didn't configure key pairs
    // check openid config -> http://localhost:8081/.well-known/openid-configuration
    // http://localhost:8081/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://springone/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256
    // you log in with test1 and test1 and you copy the secret from url
    // with that secret put it in code parameter
    // http://localhost:8081/oauth2/token?client_id=client&redirect_uri=https://springone/authorized&grant_type=authorization_code&code=hhd4qzTMcJs8OVhltDg6MK7q0gnXn0lVQTqs0SFd50zBkQr3Y71zcPxqkFDaEo-8_Ms0z5z72IQC3hPCA7qx8q-0jzG9b518nxA3JRdIjlNYJ62nFfxUUuqqVMjd4B-Z&code_verifier=qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI
    // every parameter needs to be the same as we configured here
    // in opaque token http://localhost:8081/oauth2/introspect?token=B9LJIIuNtesELk0tyOG6L_nfzeOgxfrzJomVrqueCt4pbchyPyS1x43FIsmtCOJKTPnr5nuioEJpn6kL_bo_cJyyH4VyJItAmrKniqdMv0Uxyaln55wjImZfi04pyKI3
    // you can get details about the user calling this endpoint with the access token, observe that the access token is different.

    // for authorization
    @Bean
    @Order(1)
    fun asSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)

        http.getConfigurer(OAuth2AuthorizationServerConfigurer::class.java)
            .authorizationEndpoint { a ->
                a.authenticationProviders(getAuthorizationEndpointProviders())
            }
            .oidc(Customizer.withDefaults())

        http.exceptionHandling{
            e -> e.authenticationEntryPoint(
                LoginUrlAuthenticationEntryPoint("/login")
            )
        }

        return http.build()
    }

    private fun getAuthorizationEndpointProviders(): Consumer<List<AuthenticationProvider>> {
        return Consumer { providers ->
            for (p in providers) {
                (p as? OAuth2AuthorizationCodeRequestAuthenticationProvider)?.setAuthenticationValidator(
                    CustomRedirectUriValidator()
                )
            }
        }
    }

    // for application
    @Bean
    @Order(2)
    fun appSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.formLogin()
            .and()
            .authorizeHttpRequests().anyRequest().authenticated()
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    fun authorizationServerSettings(): AuthorizationServerSettings {
        return AuthorizationServerSettings.builder().build()
    }

    @Bean
    @Throws(Exception::class)
    fun jwkSource(): JWKSource<SecurityContext> {
        val kg = KeyPairGenerator.getInstance("RSA")
        kg.initialize(2048)
        val kp = kg.generateKeyPair()
        val publicKey = kp.public as RSAPublicKey
        val privateKey = kp.private as RSAPrivateKey
        val key: RSAKey = RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())
            .build()
        val set = JWKSet(key)
        return ImmutableJWKSet(set)
    }

    @Bean
    fun oAuth2TokenCustomizer(): OAuth2TokenCustomizer<JwtEncodingContext>? {
        return OAuth2TokenCustomizer { context: JwtEncodingContext ->
            context.claims.claim("test", "test")
            context.claims.claim("authorities", context.getPrincipal<Authentication?>().authorities)
        }
    }

}
