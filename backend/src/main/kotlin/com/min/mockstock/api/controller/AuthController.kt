package com.min.mockstock.api.controller

import com.min.mockstock.application.command.AuthCommandService
import com.min.mockstock.api.dto.request.auth.LoginDTO
import com.min.mockstock.api.dto.request.auth.RegisterUserDTO
import jakarta.servlet.http.HttpSession
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
        val authCommandService: AuthCommandService
) {


    @PostMapping("/api/user/register", name = "회원가입")
    fun register(@Valid @RequestBody dto: RegisterUserDTO): ResponseEntity<Void> {
        authCommandService.registerUser(dto)
        return ResponseEntity.ok().build()
    }


    @PostMapping("/api/user/login", name = "로그인")
    fun login(@RequestBody dto: LoginDTO, session: HttpSession): ResponseEntity<Void> {
        val user = authCommandService.loginUser(dto)
        session.setAttribute("login_user", user.loginId)

        return ResponseEntity.ok().build()
    }

}