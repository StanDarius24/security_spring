package com.stannis.resourceserver.config

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken


class CustomJwtAuthenticationToken(jwt: Jwt?, authorities: Collection<GrantedAuthority?>?) :
    JwtAuthenticationToken(jwt, authorities) {
    private val blaBla = ":)"
}