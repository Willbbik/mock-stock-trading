package com.min.mockcoin.domain.user.repository;

import com.min.mockcoin.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {

    fun findByLoginId(loginId: String): Optional<User>

}