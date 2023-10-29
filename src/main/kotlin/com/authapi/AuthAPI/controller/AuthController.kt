package com.authapi.AuthAPI.controller

import com.authapi.AuthAPI.model.User
import com.authapi.AuthAPI.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam

@RestController
@RequestMapping("/api/auth")
@Api(tags = ["Authentication API"])
class AuthController(private val userService: UserService) {

    // Endpoint para registrar um novo usuário
    @ApiOperation("Register a new user")
    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        try {
            // Tenta registrar o usuário usando o serviço UserService
            userService.registerUser(user.username, user.password)
            return ResponseEntity("Usuário registrado com sucesso", HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            // Retorna uma resposta de erro se o registro falhar
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    // Endpoint para fazer login e obter um token de acesso
    @ApiOperation("Log in and get an access token")
    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseEntity<String> {
        // Verifica se o usuário existe no sistema
        val existingUser = userService.findUserByUsername(user.username)

        if (existingUser != null && existingUser.password == user.password) {
            // Gera um token de acesso JWT se as credenciais estiverem corretas
            val token = generateJwtToken(existingUser.username)
            return ResponseEntity(token, HttpStatus.OK)
        }

        // Retorna uma resposta de erro se as credenciais estiverem erradas
        return ResponseEntity("Credenciais inválidas", HttpStatus.UNAUTHORIZED)
    }

    // Função privada para gerar um token JWT
    private fun generateJwtToken(username: String): String {
        val secretKey = "suaChaveSecreta" // Substitua pela sua chave secreta
        val token = Jwts.builder()
            .setSubject(username)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()

        return token
    }
}
