package com.stannis.oauthserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OauthAuthorizationServerApplication

fun main(args: Array<String>) {
	runApplication<OauthAuthorizationServerApplication>(*args)
}
