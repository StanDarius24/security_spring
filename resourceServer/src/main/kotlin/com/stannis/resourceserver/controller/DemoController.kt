package com.stannis.resourceserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @PostMapping("/smth") // will return 403 csrf protection
    fun doSomething(): String {
        println(":)")
        return "index"
    }

}