package com.min.mockstock.domain.trade.repository;

import com.min.mockstock.domain.holding.UserHolding
import org.springframework.data.jpa.repository.JpaRepository

interface UserHoldingRepository : JpaRepository<UserHolding, Long> {
}