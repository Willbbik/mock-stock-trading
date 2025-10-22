package com.min.mockstock.domain.trade

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "trade_history")
data class TradeHistory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val tradeId: Long = 0,

    @Column(nullable = false)
    val orderId: Long,

    @Column(nullable = false)
    val symbol: String,

    @Column(nullable = false)
    val price: Double,

    @Column(nullable = false)
    val quantity: Double,

    @Column(insertable = false, updatable = false)
    val createdAt: LocalDateTime? = null
)