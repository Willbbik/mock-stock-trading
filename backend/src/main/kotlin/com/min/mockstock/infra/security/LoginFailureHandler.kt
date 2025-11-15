package com.min.mockstock.infra.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component

@Component
class LoginFailureHandler : AuthenticationFailureHandler {
    private val mapper = ObjectMapper()

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json; charset=UTF-8"

        val message = when (exception) {
            is BadCredentialsException -> "아이디 또는 비밀번호가 잘못되었습니다."
            is UsernameNotFoundException -> "아이디 또는 비밀번호가 잘못되었습니다."
            else -> "아이디 또는 비밀번호가 잘못되었습니다."
        }

        val body = mapOf(
            "status" to "fail",
            "message" to message
        )

        response.writer.write(mapper.writeValueAsString(body))
    }
}