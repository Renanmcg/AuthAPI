package com.authapi.AuthAPI.model

import jakarta.persistence.*

@Entity
@Table(name = "tokens")
data class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "token_value", unique = true)
    val tokenValue: String,

    @Column(name = "expiration_time")
    val expirationTime: Long,
)
