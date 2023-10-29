package com.authapi.AuthAPI

import com.authapi.AuthAPI.model.User
import com.authapi.AuthAPI.repository.UserRepository
import com.authapi.AuthAPI.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.password.PasswordEncoder

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    private lateinit var userService: UserService

    // Testa para registrar um usuário com sucesso
    @Test
    fun testRegisterUserSuccess() {
        userService = UserService(userRepository, passwordEncoder)

        // Simula o comportamento do userRepository
        `when`(userRepository.findByUsername("newUser")).thenReturn(null)
        `when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(User(1, "newUser", "hashedPassword"))

        val user = userService.registerUser("newUser", "password")

        assertEquals("newUser", user.username)
    }

    // Testa para tentar registrar um usuário com um nome de usuário duplicado
    @Test
    fun testRegisterUserDuplicateUsername() {
        userService = UserService(userRepository, passwordEncoder)

        // Simula o comportamento do userRepository
        `when`(userRepository.findByUsername("existingUser")).thenReturn(User(1, "existingUser", "hashedPassword"))

        // Verifiqua se a exceção é lançada ao tentar registrar um usuário com nome de usuário duplicado
        assertThrows(IllegalArgumentException::class.java) {
            userService.registerUser("existingUser", "password")
        }
    }

    // Testa para encontrar um usuário existente pelo nome de usuário
    @Test
    fun testFindUserByUsernameExisting() {
        userService = UserService(userRepository, passwordEncoder)

        // Simula o comportamento do userRepository
        `when`(userRepository.findByUsername("existingUser")).thenReturn(User(1, "existingUser", "hashedPassword"))

        val user = userService.findUserByUsername("existingUser")

        assertEquals("existingUser", user?.username)
    }

    // Testa para encontrar um usuário que não existe pelo nome de usuário
    @Test
    fun testFindUserByUsernameNonExisting() {
        userService = UserService(userRepository, passwordEncoder)

        // Simula o comportamento do userRepository
        `when`(userRepository.findByUsername("nonExistingUser")).thenReturn(null)

        val user = userService.findUserByUsername("nonExistingUser")

        assertEquals(null, user)
    }
}
