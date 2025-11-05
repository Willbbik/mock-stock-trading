package com.min.mockstock.domain.trade.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "stock_order")
data class StockOrder (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long = 0,

    @Column(nullable = false)
    val accountId: Long,

    @Column(nullable = false)
    val symbol: String,

    @Column(nullable = false)
    val status: String,

    @Column(nullable = false)
    val type: String,

    @Column(nullable = false)
    val price: Double,

    @Column(nullable = false)
    val quantity: Double,

    @Column(nullable = false)
    val filledQuantity: Double,

    @Column(insertable = false, updatable = false)
    val createdAt: LocalDateTime? = null
)