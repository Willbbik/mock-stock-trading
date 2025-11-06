package com.min.mockstock.application.command

import com.min.mockstock.common.util.Base64Utils
import com.min.mockstock.domain.user.model.User
import com.min.mockstock.api.dto.request.auth.LoginRequest
import com.min.mockstock.api.dto.request.auth.SignupRequest
import com.min.mockstock.domain.user.repository.UserRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthCommandService(
    val userRepository: UserRepository,
    val eventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun signup(param: SignupRequest) {
        userRepository.findByLoginId(param.loginId).ifPresent {
            throw IllegalArgumentException("Login ID already exists")
        }

        val user = User(
            loginId = param.loginId,
            password = Base64Utils.encode(param.password),
            name = param.name,
            email = param.email
        )

        userRepository.save(user)
//        eventPublisher.publishEvent(UserRegisteredEvent(user.id, user.loginId))
    }

    @Transactional
    fun login(param: LoginRequest): User {
        val user = userRepository.findByLoginId(param.loginId)
            .orElseThrow { IllegalArgumentException("Invalid login ID or password") }

        val decodedPassword = Base64Utils.decodeToString(user.password)
        if (!user.password.equals(decodedPassword)) {
            throw IllegalArgumentException("Invalid login ID or password")
        }

        return user
    }
}