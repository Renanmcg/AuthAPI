package com.authapi.AuthAPI.service

import com.authapi.AuthAPI.model.Token
import com.authapi.AuthAPI.repository.TokenRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(private val tokenRepository: TokenRepository) {

    fun generateToken(): Token {
        // Gere um token único (pode ser JWT, UUID, ou outro método)
        val tokenValue = generateUniqueToken()

        // Defina a data de expiração do token (por exemplo, 1 hora a partir do momento atual)
        val expirationDate = calculateExpirationDate()

        // Crie uma instância de Token com o valor do token e a data de expiração
        val token = Token(tokenValue = tokenValue, expirationTime = expirationDate.time)

        // Salve o token no banco de dados
        return tokenRepository.save(token)
    }

    fun validateToken(token: String): Boolean {
        val existingToken = tokenRepository.findByToken(token)
        return existingToken != null && !isTokenExpired(existingToken)
    }

    private fun generateUniqueToken(): String {
        // Gere um token único, por exemplo, usando UUID
        return UUID.randomUUID().toString()
    }

    private fun calculateExpirationDate(): Date {
        // Defina a data de expiração do token (por exemplo, 1 hora a partir do momento atual)
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.HOUR, 1)
        return calendar.time
    }

    private fun isTokenExpired(token: Token): Boolean {
        val now = Date()
        return token.expirationTime <= now.time
    }
}
