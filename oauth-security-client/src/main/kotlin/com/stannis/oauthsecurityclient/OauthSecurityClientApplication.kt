package com.stannis.oauthsecurityclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OauthSecurityClientApplication

fun main(args: Array<String>) {
	runApplication<OauthSecurityClientApplication>(*args)
}
