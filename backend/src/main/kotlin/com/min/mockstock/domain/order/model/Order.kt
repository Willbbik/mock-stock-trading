package com.min.mockstock.domain.order.model

import com.min.mockstock.domain.shared.CurrencyType
import com.min.mockstock.domain.shared.CustomId
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "trade_order") // 거래 주문
class Order (
    accountId: String,
    stockCode: String,
    type: OrderType,
    method: OrderMethod,
    unitPrice: BigDecimal,
    quantity: Int,
    currency: CurrencyType
) {

    @Id
    @CustomId
    @Column(name = "order_id") // 주문 ID
    lateinit var orderId: String
        protected set

    @Column(name = "account_id", nullable = false) // 계좌 ID
    var accountId: String = accountId
        protected set

    @Column(name = "stock_code", nullable = false) // 종목 코드
    var stockCode: String = stockCode
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false) // 주문 상태
    var status: OrderStatus = OrderStatus.REQUESTED
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false) // 주문 유형 (매수, 매도)
    var type: OrderType = type
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false) // 주문 방식 (시장가, 지정가)
    var method: OrderMethod = method
        protected set

    @Column(name = "unit_price", nullable = false) // 한 주당 주문 가격
    var unitPrice: BigDecimal = unitPrice
        protected set

    @Column(name = "quantity", nullable = false) // 주문 수량
    var quantity: Int = quantity
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false) // 통화 화폐
    var currency: CurrencyType = currency
        protected set

    @Column(name = "created_at", nullable = false) // 생성일시
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

}