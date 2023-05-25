package com.stannis.resourceserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController {

    @GetMapping("/demo")
//    @CrossOrigin("http://localhost:8083")
//    @CrossOrigin("*") never in production
    fun demo(): String {
        return "demo"
    }

}