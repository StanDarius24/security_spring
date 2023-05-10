package com.stannis.resourceserver.config

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.authority.SimpleGrantedAuthority


class CustomJwtAuthenticationTokenConverter :
    Converter<Jwt, CustomJwtAuthenticationToken?> {
    override fun convert(source: Jwt): CustomJwtAuthenticationToken {
        val authorities =
            source.claims["authorities"] as List<String>
        return CustomJwtAuthenticationToken(
            source, authorities.stream().map { role: String ->
                SimpleGrantedAuthority(
                    role
                )
            }.toList()
        )
    }
}