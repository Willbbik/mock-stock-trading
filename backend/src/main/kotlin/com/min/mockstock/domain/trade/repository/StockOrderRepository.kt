package com.min.mockstock.domain.trade.repository;

import com.min.mockstock.domain.trade.StockOrder
import org.springframework.data.jpa.repository.JpaRepository

interface StockOrderRepository : JpaRepository<StockOrder, Long> {
}