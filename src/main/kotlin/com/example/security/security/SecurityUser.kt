package com.example.security.security

import com.example.security.entity.User
import lombok.AllArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@AllArgsConstructor
class SecurityUser(user: User) : UserDetails {

    private var user: User

    init {
        this.user = user
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(GrantedAuthority { "read" })
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getUsername(): String? {
        return user.name
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