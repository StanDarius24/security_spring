package com.stannis.backendclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendClientApplication

fun main(args: Array<String>) {
	runApplication<BackendClientApplication>(*args)
}
