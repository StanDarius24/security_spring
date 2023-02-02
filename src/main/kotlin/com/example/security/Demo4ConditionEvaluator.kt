package com.example.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class Demo4ConditionEvaluator {

    fun condition(): Boolean {
        val a = SecurityContextHolder.getContext().authentication
        return true
    }

}