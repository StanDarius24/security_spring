package com.stannis.clientserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.web.SecurityFilterChain


@Configuration
class ProjectConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.oauth2Client()
        http.authorizeHttpRequests()
            .anyRequest()
            .permitAll()
        return http.build()
    }

    @Bean
    fun oAuth2AuthorizedClientManager(
        clientRegistrationRepository: ClientRegistrationRepository,
        auth2AuthorizedClientRepository: OAuth2AuthorizedClientRepository
    ): OAuth2AuthorizedClientManager? {
        val provider = OAuth2AuthorizedClientProviderBuilder.builder()
            .authorizationCode()
            .refreshToken()
            .clientCredentials()
            .build()

        val defaultOAuth2AuthorizedClientManager =
            DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository)
        defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider)

        return defaultOAuth2AuthorizedClientManager
    }

    @Bean
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        val c1 = ClientRegistration.withRegistrationId("1")
            .clientId("client")
            .clientSecret("secret")
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .tokenUri("http://localhost:8081/oauth2/token")
            .scope(OidcScopes.OPENID)
            .build()

        return InMemoryClientRegistrationRepository(c1)
    }

}