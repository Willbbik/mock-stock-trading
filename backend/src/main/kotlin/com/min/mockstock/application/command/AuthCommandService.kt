package com.min.mockstock.application.command

import com.min.mockstock.api.dto.request.auth.SignupRequest
import com.min.mockstock.application.event.UserSignupEvent
import com.min.mockstock.domain.user.model.User
import com.min.mockstock.domain.user.repository.UserRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthCommandService(
    val userRepository: UserRepository,
    val eventPublisher: ApplicationEventPublisher,
    val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun signup(param: SignupRequest) {
        userRepository.findByLoginId(param.loginId).ifPresent {
            throw IllegalArgumentException("Login ID already exists")
        }

        val user = User(
            loginId = param.loginId,
            password = passwordEncoder.encode(param.password),
            name = param.name,
            email = param.email
        )

        userRepository.save(user)
        eventPublisher.publishEvent(UserSignupEvent(user.userId, user.loginId))
    }

}