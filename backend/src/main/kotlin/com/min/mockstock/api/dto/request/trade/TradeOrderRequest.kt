package com.min.mockstock.api.dto.request.trade

import com.min.mockstock.domain.order.model.OrderType

data class TradeOrderRequest(
    var userId: Long,
    val stockCode: String,
    val orderType: OrderType,
    val side: String,
    val volume: Double,
    val price: Double,
    val ordType: String
)
