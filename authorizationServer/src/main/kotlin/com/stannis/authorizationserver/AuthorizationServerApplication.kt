<<<<<<<< HEAD:serverAuthorization/src/main/kotlin/com/stannis/serverauthorization/ServerAuthorizationApplication.kt
package com.stannis.serverauthorization
========
package com.stannis.authorizationserver
>>>>>>>> feature/authorizationServer:authorizationServer/src/main/kotlin/com/stannis/authorizationserver/AuthorizationServerApplication.kt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
<<<<<<<< HEAD:serverAuthorization/src/main/kotlin/com/stannis/serverauthorization/ServerAuthorizationApplication.kt
class ServerAuthorizationApplication

fun main(args: Array<String>) {
	runApplication<ServerAuthorizationApplication>(*args)
========
class AuthorizationServerApplication

fun main(args: Array<String>) {
	runApplication<AuthorizationServerApplication>(*args)
>>>>>>>> feature/authorizationServer:authorizationServer/src/main/kotlin/com/stannis/authorizationserver/AuthorizationServerApplication.kt
}
