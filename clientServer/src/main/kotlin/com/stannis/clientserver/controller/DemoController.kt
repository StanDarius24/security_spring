package com.stannis.clientserver.controller

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(oAuth2AuthorizedClientManager: OAuth2AuthorizedClientManager) {

    private lateinit var oAuth2AuthorizedClientManager: OAuth2AuthorizedClientManager

    init {
        this.oAuth2AuthorizedClientManager = oAuth2AuthorizedClientManager
    }

    @GetMapping("/token")
    fun token(): String {
        val request = OAuth2AuthorizeRequest // should be in a proxy but yeah
            .withClientRegistrationId("1")
            .principal("client")
            .build()

        val client = oAuth2AuthorizedClientManager.authorize(request)

        return client!!.accessToken.tokenValue
    }

}