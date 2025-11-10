package com.min.mockstock.domain.order.model

import com.min.mockstock.domain.shared.CustomId
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "trade_order_history") // 거래 주문 이력
class OrderHistory(
    orderId: String,
    status: OrderStatus,
    previousQuantity: Int? = null,
    newQuantity: Int? = null,
    previousUnitPrice: BigDecimal? = null,
    newUnitPrice: BigDecimal? = null
) {

    @Id
    @CustomId
    @Column(name = "history_id", nullable = false) // 거래 주문 이력 ID
    lateinit var historyId: String
        protected set

    @Column(name = "order_id", nullable = false) // 주문 ID
    var orderId: String = orderId
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false) // 주문 상태
    var status: OrderStatus = status
        protected set

    @Column(name = "previous_quantity") // 이전 주문 수량
    var previousQuantity: Int? = previousQuantity
        protected set

    @Column(name = "new_quantity") // 변경된 주문 수량
    var newQuantity: Int? = newQuantity
        protected set

    @Column(name = "previous_unit_price") // 이전 주문 금액
    var previousUnitPrice: BigDecimal? = previousUnitPrice
        protected set

    @Column(name = "new_unit_price") // 변경된 주문 금액
    var newUnitPrice: BigDecimal? = newUnitPrice
        protected set

    @Column(name = "history_created_at", nullable = false) // 상태 변경 일시
    var historyCreatedAt: LocalDateTime = LocalDateTime.now()
        protected set

}