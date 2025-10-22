package com.min.mockcoin.user

import com.min.mockcoin.domain.user.User
import com.min.mockcoin.domain.user.dto.RegisterUserDTO
import com.min.mockcoin.domain.user.event.UserRegisteredEvent
import com.min.mockcoin.domain.user.repository.UserRepository
import com.min.mockcoin.service.impl.AuthServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
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
class AuthServiceTest {

    @InjectMocks
    lateinit var authService: AuthServiceImpl

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var eventPublisher: ApplicationEventPublisher

    @Test
    fun `중복된 아이디로 회원가입 시도 시 에러 발생`() {

        val registerUserDTO = RegisterUserDTO(
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
            authService.registerUser(registerUserDTO)
        }

        // verify
        verify(userRepository).findByLoginId("testUser")
        verify(userRepository, never()).save(any())
        verify(eventPublisher, never()).publishEvent(any())
    }

}