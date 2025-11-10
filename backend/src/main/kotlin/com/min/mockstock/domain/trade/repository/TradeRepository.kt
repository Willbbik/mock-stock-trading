package com.min.mockstock.domain.trade.repository;

import com.min.mockstock.domain.trade.model.Trade
import org.springframework.data.jpa.repository.JpaRepository

interface TradeRepository : JpaRepository<Trade, String> {
}