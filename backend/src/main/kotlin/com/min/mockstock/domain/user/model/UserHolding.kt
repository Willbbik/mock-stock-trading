package com.min.mockstock.domain.user.model

import com.min.mockstock.domain.shared.CustomId
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "user_holding")
class UserHolding(
    userId: String,
    stockCode: String,
    quantity: Int,
    averagePrice: BigDecimal
) {

    @Id
    @CustomId
    @Column(name = "holding_id")
    lateinit var holdingId: String
        protected set

    @Column(name = "user_id", nullable = false)
    var userId: String = userId
        protected set

    @Column(name = "stock_code", nullable = false)
    var stockCode: String = stockCode
        protected set

    @Column(name = "quantity", nullable = false)
    var quantity: Int = quantity
        protected set

    @Column(name = "average_price", nullable = false)
    var averagePrice: BigDecimal = averagePrice
        protected set
}