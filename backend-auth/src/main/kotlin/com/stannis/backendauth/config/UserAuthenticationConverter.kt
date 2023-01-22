package com.stannis.backendauth.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.stannis.backendauth.dto.UserDto
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationConverter
import org.springframework.stereotype.Component

@Component
class UserAuthenticationConverter: AuthenticationConverter {

    companion object {
        private val MAPPER = ObjectMapper()
    }

    override fun convert(request: HttpServletRequest): Authentication {
        val userDto = try {
            MAPPER.readValue(request.inputStream, UserDto::class.java)
        } catch (ex: Exception) {
            throw IllegalStateException(ex)
        }
        return UsernamePasswordAuthenticationToken.unauthenticated(userDto.login, userDto.password)
    }

}