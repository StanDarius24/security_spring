package com.stannis.oauthresourceserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OauthResourceServerApplication

fun main(args: Array<String>) {
	runApplication<OauthResourceServerApplication>(*args)
}
