package com.stannis.authorizationserver.model

import com.stannis.authorizationserver.entity.AuthUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(private val authUser: AuthUser) : UserDetails {

    override fun getAuthorities(): List<GrantedAuthority> {
        return listOf(GrantedAuthority { authUser.authority })
    }

    override fun getPassword(): String {
        return authUser.password
    }

    override fun getUsername(): String {
        return authUser.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}