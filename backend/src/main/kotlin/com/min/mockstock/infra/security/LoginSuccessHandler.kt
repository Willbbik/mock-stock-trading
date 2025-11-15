package com.min.mockstock.infra.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class LoginSuccessHandler : AuthenticationSuccessHandler {

    private val mapper = ObjectMapper()

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        response.status = HttpServletResponse.SC_OK
        response.contentType = "application/json; charset=UTF-8"

        val body = mapOf(
            "status" to "success",
            "message" to "로그인 성공",
            "username" to authentication.name
        )

        response.writer.write(mapper.writeValueAsString(body))
    }
}