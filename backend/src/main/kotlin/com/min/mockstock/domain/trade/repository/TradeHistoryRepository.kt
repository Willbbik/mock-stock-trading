package com.min.mockstock.domain.trade.repository;

import com.min.mockstock.domain.trade.model.TradeHistory
import org.springframework.data.jpa.repository.JpaRepository

interface TradeHistoryRepository : JpaRepository<TradeHistory, Long> {
}