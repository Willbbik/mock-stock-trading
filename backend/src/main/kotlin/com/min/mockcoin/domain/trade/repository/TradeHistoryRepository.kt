package com.min.mockcoin.domain.trade.repository;

import com.min.mockcoin.domain.trade.TradeHistory
import org.springframework.data.jpa.repository.JpaRepository

interface TradeHistoryRepository : JpaRepository<TradeHistory, Long> {
}