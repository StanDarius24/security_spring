package com.example.controller

import com.example.dto.Message
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DemoController {

    @GetMapping("/hello1")
    fun hello1(): String {
        return "Hello World 1!"
    }

    @GetMapping("/hello2")
    fun hello2(): String {
        return "Hello World 2!"
    }

    @PostMapping("/hello3")
    fun hello3(@RequestBody example: Message): Message{
        println(example.text)
        println(example.id)
        return example
    }

}