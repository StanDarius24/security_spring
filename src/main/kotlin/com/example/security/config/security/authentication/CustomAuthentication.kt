package com.example.security.config.security.authentication

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

@Getter
@Setter
@AllArgsConstructor
class CustomAuthentication(authentication: Boolean, key: String?) : Authentication { // SEGREGATION :(((((((((((((((((

    private val authentication: Boolean

    val key: String?

    init {
        this.authentication = authentication
        this.key = key
    }

    override fun getName(): String {
        TODO("Not yet implemented")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getCredentials(): Any {
        TODO("Not yet implemented")
    }

    override fun getDetails(): Any {
        TODO("Not yet implemented")
    }

    override fun getPrincipal(): Any {
        TODO("Not yet implemented")
    }

    override fun isAuthenticated(): Boolean {
        return authentication
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        TODO("Not yet implemented")
    }

}