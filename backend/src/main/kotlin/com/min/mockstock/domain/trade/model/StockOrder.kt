package com.min.mockstock.domain.trade.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "stock_order") // 주문 관리
data class StockOrder (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id") // 주문 ID
    val orderId: Long = 0,

    @Column(name = "account_id", nullable = false) // 계좌 ID
    val accountId: Long,

    @Column(name = "symbol", nullable = false) // 종목 코드
    val symbol: String,

    @Column(name = "status", nullable = false) // 주문 상태
    val status: String,

    @Column(name = "type", nullable = false) // 주문 유형
    val type: String,

    @Column(name = "price", nullable = false) // 주문 가격
    val price: Double,

    @Column(name = "quantity", nullable = false) // 주문 수량
    val quantity: Double,

    @Column(name = "filled_quantity", nullable = false) // 체결된 주문 수량
    val filledQuantity: Double,

    @Column(name = "created_at", insertable = false, updatable = false) // 생성일시
    val createdAt: LocalDateTime? = null
)