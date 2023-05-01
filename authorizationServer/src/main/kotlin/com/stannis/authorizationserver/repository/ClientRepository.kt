package com.stannis.authorizationserver.repository

import com.stannis.authorizationserver.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface ClientRepository: JpaRepository<Client, Long> {

    @Query("""
        select s from Client s where 
        s.clientId = :clientId
    """
    )
    fun findByClientId(clientId: String): Optional<Client>

}