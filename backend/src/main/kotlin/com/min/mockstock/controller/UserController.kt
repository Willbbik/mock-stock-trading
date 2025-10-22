package com.min.mockstock.controller

import com.min.mockstock.domain.user.dto.LoginDTO
import com.min.mockstock.domain.user.dto.RegisterUserDTO
import com.min.mockstock.service.AuthService
import jakarta.servlet.http.HttpSession
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        val authService: AuthService
) {


    @PostMapping("/api/user/register", name = "회원가입")
    fun register(@Valid @RequestBody dto: RegisterUserDTO): ResponseEntity<Void> {
        authService.registerUser(dto)
        return ResponseEntity.ok().build()
    }


    @PostMapping("/api/user/login", name = "로그인")
    fun login(@RequestBody dto: LoginDTO, session: HttpSession): ResponseEntity<Void> {
        val user = authService.loginUser(dto)
        session.setAttribute("login_user", user.loginId)

        return ResponseEntity.ok().build()
    }

}