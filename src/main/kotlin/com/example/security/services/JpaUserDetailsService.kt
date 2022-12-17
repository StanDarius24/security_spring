package com.example.security.services

import com.example.security.repository.UserRepository
import com.example.security.security.SecurityUser
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class JpaUserDetailsService: UserDetailsService {

    @Autowired
    private final lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails? {
        val u = userRepository.findByName(username)
        return u.map { SecurityUser(it) }.orElse(null)
    }

}