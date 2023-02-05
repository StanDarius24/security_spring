package com.stannis.serverauthorization

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServerAuthorizationApplication

fun main(args: Array<String>) {
	runApplication<ServerAuthorizationApplication>(*args)
}
