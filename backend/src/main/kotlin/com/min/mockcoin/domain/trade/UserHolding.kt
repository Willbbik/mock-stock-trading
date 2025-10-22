package com.min.mockcoin.domain.holding

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_holding")
data class UserHolding(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val holdingId: Long = 0,

    @Column(nullable = false)
    val accountId: Long,

    @Column(nullable = false)
    val symbol: String,

    @Column(nullable = false)
    val quantity: Double,

    @Column(nullable = false)
    val averagePurchaseCost: Double,

    @Column(nullable = false)
    val reservedSellQty: Double,

    @Column(insertable = false, updatable = false)
    val createdAt: LocalDateTime? = null
)