package com.stannis.resourceserver.controller

import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CsrfController {

    @GetMapping("/v1/csrf")
    fun csrf(token: CsrfToken): CsrfToken {
        return token
    }

}