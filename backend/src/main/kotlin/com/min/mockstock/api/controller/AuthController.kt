package com.min.mockstock.api.controller

import com.min.mockstock.api.dto.request.auth.loginRequest
import com.min.mockstock.api.dto.request.auth.signupRequest
import com.min.mockstock.application.command.AuthCommandService
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


    @PostMapping("/api/user/signup", name = "회원가입")
    fun signup(@Valid @RequestBody dto: signupRequest): ResponseEntity<Void> {
        authCommandService.signup(dto)
        return ResponseEntity.ok().build()
    }


    @PostMapping("/api/user/login", name = "로그인")
    fun login(@RequestBody dto: loginRequest, session: HttpSession): ResponseEntity<Void> {
        val user = authCommandService.login(dto)
        session.setAttribute("login_user", user.loginId)

        return ResponseEntity.ok().build()
    }

}