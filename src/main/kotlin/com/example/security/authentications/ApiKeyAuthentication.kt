package com.example.security.authentications

import lombok.RequiredArgsConstructor
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

@RequiredArgsConstructor
class ApiKeyAuthentication(key: String?): Authentication {

    private var authenticated: Boolean = false
    private var key: String?

    fun getKey(): String {
        return key.orEmpty()
    }

    init {
        this.key = key
    }

    override fun getName(): String {
        return "API Key"
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getCredentials(): Any {
        return "all"
    }

    override fun getDetails(): Any {
        return "all"
    }

    override fun getPrincipal(): Any {
        return com.example.security.authentications.principal.Principal()
    }

    override fun isAuthenticated(): Boolean {
        return authenticated
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.authenticated = isAuthenticated
    }

}