package com.min.mockcoin.domain.trade.repository;

import com.min.mockcoin.domain.holding.UserHolding
import org.springframework.data.jpa.repository.JpaRepository

interface UserHoldingRepository : JpaRepository<UserHolding, Long> {
}