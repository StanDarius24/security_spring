package com.stannis.authorizationserver.entity

import jakarta.persistence.*

@Entity
@Table(name = "client")
open class Client (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long?,

    @Column(nullable = false)
    open var clientId: String,

    @Column(nullable = false)
    open var secret: String,

    @Column(nullable = false)
    open var redirectUri: String,

    @Column(nullable = false)
    open var scope: String,

    @Column(nullable = false)
    open var authMethod: String,

    @Column(nullable =false)
    open var grantType: String

)
