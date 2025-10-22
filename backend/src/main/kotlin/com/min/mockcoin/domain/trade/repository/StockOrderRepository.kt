package com.min.mockcoin.domain.trade.repository;

import com.min.mockcoin.domain.trade.StockOrder
import org.springframework.data.jpa.repository.JpaRepository

interface StockOrderRepository : JpaRepository<StockOrder, Long> {
}