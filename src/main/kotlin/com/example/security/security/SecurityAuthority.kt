package com.example.security.security

import com.example.security.entity.Authority
import org.springframework.security.core.GrantedAuthority

class SecurityAuthority(authority: Authority) : GrantedAuthority {

    private var authority: Authority

    init {
        this.authority = authority
    }

    override fun getAuthority(): String? {
        return authority.name
    }

}