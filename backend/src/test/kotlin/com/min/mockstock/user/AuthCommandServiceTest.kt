package com.min.mockstock.user

import com.min.mockstock.api.dto.request.auth.SignupRequest
import com.min.mockstock.application.command.AuthCommandService
import com.min.mockstock.domain.user.model.User
import com.min.mockstock.domain.user.repository.UserRepository
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.context.ApplicationEventPublisher
import java.util.*
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class AuthCommandServiceTest {

    @InjectMocks
    lateinit var authService: AuthCommandService

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var eventPublisher: ApplicationEventPublisher

    @Test
    fun `중복된 아이디로 회원가입 시도 시 에러 발생`() {

        val registerUserDTO = SignupRequest(
                loginId = "testUser",
                password = "testUser",
                name = "Test User",
                email = "test@example.com"
        )

        val user = User (
            loginId = "testUser",
            password = "testUser",
            name = "Test User",
            email = "test@example.com"
        )

        `when`(userRepository.findByLoginId("testUser")).thenReturn(Optional.of(user))

        assertThrows<IllegalArgumentException> {
            authService.signup(registerUserDTO)
        }

        // verify
        verify(userRepository).findByLoginId("testUser")
        verify(userRepository, never()).save(any())
        verify(eventPublisher, never()).publishEvent(any())
    }

}