package com.stannis.authorizationserver.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "auth_user")

open class AuthUser(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_auth_generator")
    @GenericGenerator(
        name = "sequence_auth_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            org.hibernate.annotations.Parameter(name = "sequence_name", value = "auth_user_id_seq"),
            org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
            org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        ]
    )
    open var id: Long,

    @Column(nullable = false)
    open var username: String,

    @Column(nullable = false)
    open var password: String,

    @Column(nullable = false)
    open var authority: String

)