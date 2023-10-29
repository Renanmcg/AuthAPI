package com.authapi.AuthAPI.repository

import com.authapi.AuthAPI.model.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    fun findByToken(token: String): Token?
}
