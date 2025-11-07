package com.min.mockstock.domain.user.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_holding")   // 사용자 주식 보유 현황
data class UserHolding (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holding_id") // 주식 보유 현황 ID
    val holdingId: Long = 0,

    @Column(name = "account_id", nullable = false) // 계좌 ID
    val accountId: Long,

    @Column(name = "symbol", nullable = false) // 종목 코드
    val symbol: String,

    @Column(name = "quantity", nullable = false) // 보유 수량
    val quantity: Double,

    @Column(name = "average_purchase_cost", nullable = false) // 평균 매수 금액
    val averagePurchaseCost: Double,

    @Column(name = "reserved_sell_qty", nullable = false) // 매도 주문 대기 수량
    val reservedSellQty: Double,

    @Column(name = "created_at", insertable = false, updatable = false) // 생성일시
    val createdAt: LocalDateTime? = null
)