package com.stannis.backendauth.repository

import com.stannis.backendauth.entity.AuthUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<AuthUser, Long> {

    fun findByLogin(login: String): Optional<AuthUser>

}