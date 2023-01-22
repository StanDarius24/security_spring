package com.stannis.backendauth.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "auth_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
data class AuthUser(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_auth_generator")
    @GenericGenerator(
        name = "sequence_auth_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            org.hibernate.annotations.Parameter(name = "sequence_name", value = "service_auth.auth_user_id_seq"),
            org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
            org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        ]
    )
    var id: Long,

    @Column(nullable = false)
    var login: String,

    @Column(nullable = false)
    var password: String

)
