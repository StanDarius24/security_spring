package com.stannis.oauthserver.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var firstName: String?,
    var lastName: String?,
    var email: String?,

    @Column(length = 60)
    var password: String?,

    var role: String?,
    var enabled: Boolean = false

)
