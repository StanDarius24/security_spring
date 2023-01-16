package com.stannis.oauthserver.service

import com.stannis.oauthserver.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
@Transactional
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(11)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { userRepository.findByEmail(it) } ?: throw Exception("User not found")
        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password,
            user.enabled,
            true,
            true,
            true,
            user.role?.let { getAuthorities(listOf(it)) } ?: getAuthorities(listOf(""))
        )
    }

    private fun getAuthorities(listOf: List<String>): List<GrantedAuthority> {
        val authorities: ArrayList<GrantedAuthority> = ArrayList()
        listOf.forEach { role ->
            authorities.add(SimpleGrantedAuthority(role))
        }
        return authorities
    }

}

