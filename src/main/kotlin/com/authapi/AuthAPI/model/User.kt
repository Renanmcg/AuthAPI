package com.authapi.AuthAPI.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Column

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
