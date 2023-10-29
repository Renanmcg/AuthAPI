package com.authapi.AuthAPI.service

import com.authapi.AuthAPI.model.User
import com.authapi.AuthAPI.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun registerUser(username: String, password: String): User {
        // Verificar se o nome de usuário já existe
        if (userRepository.findByUsername(username) != null) {
            throw IllegalArgumentException("Nome de usuário já existe")
        }

        // Criar uma instância de User com os dados fornecidos
        val user = User(username = username, password = passwordEncoder.encode(password))

        // Salvar o usuário no banco de dados
        return userRepository.save(user)
    }

    fun findUserByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}
