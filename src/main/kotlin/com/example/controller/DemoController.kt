package com.example.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('read')")
    fun hello(): String {
        return "Hello World!"
    }

    @GetMapping("/demo")
    @PreAuthorize("hasAnyAuthority('read', 'write')")
    fun demo(): String {
        return "Hello Demo!"
    }

    @GetMapping("/demo2/{something}")
    @PreAuthorize(
            """
                hasAuthority('write') or 
                #something == authentication.name
            """
    ) // not recommended hard to debug
    fun index(@PathVariable("something") something: String): String {
        return "Hello Index!"
    }

    // do this instead
    @GetMapping("/demo3/{something}")
    @PreAuthorize("@demo4ConditionEvaluator.condition()") // using a bean
    fun index2(@PathVariable("something") something: String): String {
        return "Hello Index2!"
    }


}