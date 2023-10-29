package com.authapi.AuthAPI.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Column


@Entity
@Table(name = "users") // Nome da tabela no banco de dados
data class User(
    @Id
    @Column(name = "id")
    val id: Long? = null, // Chave primária

    @Column(name = "username", unique = true)
    val username: String,

    @Column(name = "password")
    val password: String

)
