package com.example.security.authentications.principal

class Principal: java.security.Principal {
    override fun getName(): String {
        return "API Key"
    }
}