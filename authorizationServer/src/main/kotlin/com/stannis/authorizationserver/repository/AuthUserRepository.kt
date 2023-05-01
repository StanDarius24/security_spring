package com.stannis.authorizationserver.repository

import com.stannis.authorizationserver.entity.AuthUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface AuthUserRepository: JpaRepository<AuthUser, Long> {

    @Query("""
        select u from AuthUser u where 
                u.username = :name
    """)
    fun findByUsername(name: String): Optional<AuthUser>

}