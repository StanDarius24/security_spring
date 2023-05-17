package com.stannis.clientserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController() {


    @GetMapping("/token")
    fun token(): String {
        return "demo!"
    }

}