package com.min.mockstock.infra.security

import com.min.mockstock.domain.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user = userRepository.findByLoginId(username)
            .orElseThrow { IllegalArgumentException("Invalid login ID or password") }

//        val decodedPassword = Base64Utils.decodeToString(user.password)
//        val decodedPassword = Base64Utils.decodeToString(user.password)

//        if (!user.password.equals(decodedPassword)) {
//            throw IllegalArgumentException("Invalid login ID or password")
//        }

        return User(
            user.loginId,
            user.password,
            listOf(SimpleGrantedAuthority("ROLE_USER"))
        )
    }

}