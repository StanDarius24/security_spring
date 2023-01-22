package com.stannis.backendresource.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ResourceController {

    @GetMapping("/message")
    fun getMessage(): String {
        return "Hello from backend-resource"
    }

}