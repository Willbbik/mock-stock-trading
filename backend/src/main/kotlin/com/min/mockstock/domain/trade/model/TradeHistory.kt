package com.min.mockstock.domain.trade.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "trade_history") // 거래 체결 기록
data class TradeHistory (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id") // 거래 기록 ID
    val tradeId: Long = 0,

    @Column(name = "order_id", nullable = false) // 주문 ID
    val orderId: Long,

    @Column(name = "symbol", nullable = false) // 종목 코드
    val symbol: String,

    @Column(name = "price", nullable = false) // 실제 체결된 가격
    val price: Double,

    @Column(name = "quantity", nullable = false) // 체결 주문 수량
    val quantity: Double,

    @Column(name = "created_at", insertable = false, updatable = false) // 생성일시
    val createdAt: LocalDateTime? = null
)