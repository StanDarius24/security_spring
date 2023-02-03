package com.example.controller

import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('read')")
    fun hello(): String {
        return "Hello World 22!"
    }

    @GetMapping("/demo")
    @PreAuthorize("hasAnyAuthority('read', 'write')")
    fun demo(): String {
        return "Hello Demo 21!"
    }

    @GetMapping("/demo2/{something}")
    @PreAuthorize(
            """
                hasAuthority('write') or 
                #something == authentication.name
            """
    ) // not recommended hard to debug
    fun index(@PathVariable("something") something: String): String {
        return "Hello Index 1!"
    }

    // do this instead
    @GetMapping("/demo3/{something}")
    @PreAuthorize("@demo4ConditionEvaluator.condition()") // using a bean
    fun index2(@PathVariable("something") something: String): String {
        return "Hello Index 2!"
    }

    @GetMapping("/demo4")
    @PostAuthorize("returnObject == 'Hello Index 3!'") // is mainly used when we want to restrict the access to some returned value
    fun index3(): String {
        println(":)") // never use @PostAuthorize with methods that change data in the database
        return "Hello Index 3!"
    }

}