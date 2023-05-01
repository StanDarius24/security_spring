package com.stannis.authorizationserver.service

import com.stannis.authorizationserver.model.SecurityUser
import com.stannis.authorizationserver.repository.AuthUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class UserDetailsService: UserDetailsService {

    @Autowired
    private lateinit var authUserRepository: AuthUserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = username?.let { authUserRepository.findByUsername(it) }?.orElseThrow()
        return user?.let { SecurityUser(it) }
    }

}