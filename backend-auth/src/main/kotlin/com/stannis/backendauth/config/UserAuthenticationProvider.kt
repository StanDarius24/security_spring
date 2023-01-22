package com.stannis.backendauth.config

import com.stannis.backendauth.entity.AuthUser
import com.stannis.backendauth.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.nio.CharBuffer
import java.util.*


@Component
@RequiredArgsConstructor
class UserAuthenticationProvider : AuthenticationProvider {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
        val login: String = authentication.name
        val password: String = authentication.credentials.toString()
        val oUser: Optional<AuthUser> = userRepository.findByLogin(login)
        if (oUser.isEmpty) {
            return null
        }
        val (_, _, password1) = oUser.get()
        return if (passwordEncoder.matches(CharBuffer.wrap(password), password1)) {
            UsernamePasswordAuthenticationToken.authenticated(login, password, Collections.emptyList())
        } else null
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java == authentication
    }

}