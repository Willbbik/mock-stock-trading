package com.min.mockstock.domain.user.repository;

import com.min.mockstock.domain.user.model.UserHolding
import org.springframework.data.jpa.repository.JpaRepository

interface UserHoldingRepository : JpaRepository<UserHolding, String> {
}