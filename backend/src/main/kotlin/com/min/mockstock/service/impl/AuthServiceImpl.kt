package com.min.mockstock.service.impl

import com.min.mockstock.common.Base64Utils
import com.min.mockstock.domain.user.User
import com.min.mockstock.domain.user.dto.LoginDTO
import com.min.mockstock.domain.user.dto.RegisterUserDTO
import com.min.mockstock.domain.user.event.UserRegisteredEvent
import com.min.mockstock.domain.user.repository.UserRepository
import com.min.mockstock.service.AuthService
import org.springframework.transaction.annotation.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
        val userRepository: UserRepository,
        val eventPublisher: ApplicationEventPublisher
) : AuthService {

    @Transactional
    override fun registerUser(param: RegisterUserDTO) {
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
        eventPublisher.publishEvent(UserRegisteredEvent(user.id, user.loginId))
    }

    @Transactional
    override fun loginUser(param: LoginDTO): User {
        val user = userRepository.findByLoginId(param.loginId)
                .orElseThrow { IllegalArgumentException("Invalid login ID or password") }

        val decodedPassword = Base64Utils.decodeToString(user.password)
        if (!user.password.equals(decodedPassword)) {
            throw IllegalArgumentException("Invalid login ID or password")
        }

        return user
    }
}