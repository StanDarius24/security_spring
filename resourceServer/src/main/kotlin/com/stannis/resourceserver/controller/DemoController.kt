package com.stannis.resourceserver.controller

import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {

    fun demo(): String {
        return "Demo!"
    }

}