package com.stannis.backendclient.controller

import lombok.RequiredArgsConstructor
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequiredArgsConstructor
class MessageController {

    private final lateinit var webClient: WebClient

    @GetMapping("/messages")
    fun getMessages(@RegisteredOAuth2AuthorizedClient("messages-client-authorization-code") authorizedClient: OAuth2AuthorizedClient): String {
        return webClient.get()
                .uri("http://backend-resource:8082/message")
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String::class.java)
                .block()!!
    }

}