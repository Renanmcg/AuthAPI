package com.authapi.AuthAPI.controller

import com.authapi.AuthAPI.model.User
import com.authapi.AuthAPI.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        try {
            userService.registerUser(user.username, user.password)
            return ResponseEntity("Usuário registrado com sucesso", HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseEntity<String> {
        val existingUser = userService.findUserByUsername(user.username)

        if (existingUser != null && existingUser.password == user.password) {
            val token = generateJwtToken(existingUser.username)
            return ResponseEntity(token, HttpStatus.OK)
        }

        return ResponseEntity("Credenciais inválidas", HttpStatus.UNAUTHORIZED)
    }

    private fun generateJwtToken(username: String): String {
        val secretKey = "suaChaveSecreta" // Substitua pela sua chave secreta
        val token = Jwts.builder()
            .setSubject(username)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()

        return token
    }
}
