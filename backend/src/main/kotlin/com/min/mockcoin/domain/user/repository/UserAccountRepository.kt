package com.min.mockcoin.domain.user.repository;

import com.min.mockcoin.domain.user.UserAccount
import org.springframework.data.jpa.repository.JpaRepository

interface UserAccountRepository : JpaRepository<UserAccount, Long> {
}