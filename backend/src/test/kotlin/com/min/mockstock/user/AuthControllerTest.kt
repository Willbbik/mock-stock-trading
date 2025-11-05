package com.min.mockstock.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.min.mockstock.api.controller.AuthController
import com.min.mockstock.domain.user.dto.RegisterUserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@WebMvcTest(AuthController::class)
class AuthControllerTest {

    private var objectMapper: ObjectMapper = ObjectMapper()

    @MockitoBean
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var mockMvc: MockMvc

    fun request(dto: RegisterUserDTO): ResultActions {
        return mockMvc.perform(
                post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        )
    }

    @Test
    fun `누락된 파라미터가 있는지 체크`() {
        // Given
        val dto = RegisterUserDTO (
                loginId = "testUser",
                password = "",
                name = "Test User",
                email = "test@example.com"
        )

        // When & Then
        request(dto).andExpect(status().isBadRequest)
    }


    @Test
    fun `정상 회원가입`() {
        // Given
        val dto = RegisterUserDTO (
                loginId = "testUser",
                password = "temppassword",
                name = "TestUser",
                email = "test@example.com"
        )

        // When & Then
        request(dto).andExpect(status().isOk)
    }


}