package com.example.security.controllers

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
     fun test(): String { // BasicAuthenticationFilter
        val u = SecurityContextHolder.getContext().authentication
        u.authorities.forEach{ println(it) }
        return "Test"
    }

}