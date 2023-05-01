package com.stannis.authorizationserver.service

import com.stannis.authorizationserver.mapper.security.ClientMapper
import com.stannis.authorizationserver.repository.ClientRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.stereotype.Service

@Service
@Transactional
class ClientService: RegisteredClientRepository {

    @Autowired
    private lateinit var clientRepository: ClientRepository

    override fun save(registeredClient: RegisteredClient) {
        clientRepository.save(ClientMapper.from(registeredClient))
    }

    override fun findById(id: String): RegisteredClient {
        return ClientMapper.to(
            clientRepository.findById(id.toLong()).orElseThrow()
        )
    }

    override fun findByClientId(clientId: String): RegisteredClient {
        return ClientMapper.to(
            clientRepository.findByClientId(clientId).orElseThrow()
        )
    }

}