package com.min.mockstock.domain.user.repository;

import com.min.mockstock.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {

    fun findByLoginId(loginId: String): Optional<User>

}