package com.min.mockstock.domain.trade.model

import com.min.mockstock.domain.order.model.OrderStatus
import com.min.mockstock.domain.shared.CustomId
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "trade") // 체결 거래 기록
class Trade (
    orderId: String,
    status: TradeStatus,
    stockCode: String,
    executionPrice: BigDecimal,
    quantity: Int,
    executedAmount: BigDecimal
) {


    @Id
    @CustomId
    @Column(name = "trade_id") // 체결 거래 기록 ID
    lateinit var tradeId: String
        protected set

    @Column(name = "order_id", nullable = false) // 주문 ID
    var orderId: String = orderId
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false) // 거래 상태
    var status: TradeStatus = status
        protected set

    @Column(name = "stock_code", nullable = false) // 종목 코드
    var stockCode: String = stockCode
        protected set

    @Column(name = "execution_price", nullable = false) // 한 주당 체결가
    var executionPrice: BigDecimal = executionPrice
        protected set

    @Column(name = "quantity", nullable = false) // 체결 주문 수량
    var quantity: Int = quantity
        protected set

    @Column(name = "executed_amount", nullable = false) // 총 체결 금액
    var executedAmount: BigDecimal = executedAmount
        protected set

    @Column(name = "created_at", nullable = false) // 생성일시
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

}