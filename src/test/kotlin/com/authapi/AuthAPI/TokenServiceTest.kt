package com.authapi.AuthAPI.service

import com.authapi.AuthAPI.model.Token
import com.authapi.AuthAPI.repository.TokenRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenServiceTest(private val tokenRepository: TokenRepository) {

    // Gera um novo token
    fun generateToken(): Token {
        // Gera um token único (pode ser JWT, UUID, ou outro método)
        val tokenValue = generateUniqueToken()

        // Calcula a data de expiração do token (por exemplo, 1 hora a partir do momento atual)
        val expirationDate = calculateExpirationDate()

        // Cria uma instância de Token com o valor do token e a data de expiração
        val token = Token(tokenValue = tokenValue, expirationTime = expirationDate.time)

        // Salva o token no banco de dados
        return tokenRepository.save(token)
    }

    // Valida se um token é válido
    fun validateToken(token: String): Boolean {
        // Verifica se o token existe no banco de dados
        val existingToken = tokenRepository.findByToken(token)

        // Retorna true se o token existe e não está expirado
        return existingToken != null && !isTokenExpired(existingToken)
    }

    // Gera um token único (exemplo: UUID)
    private fun generateUniqueToken(): String {
        return UUID.randomUUID().toString()
    }

    // Calcula a data de expiração do token (exemplo: 1 hora a partir do momento atual)
    private fun calculateExpirationDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.HOUR, 1)
        return calendar.time
    }

    // Verifica se um token está expirado
    private fun isTokenExpired(token: Token): Boolean {
        val now = Date()
        return token.expirationTime <= now.time
    }
}
