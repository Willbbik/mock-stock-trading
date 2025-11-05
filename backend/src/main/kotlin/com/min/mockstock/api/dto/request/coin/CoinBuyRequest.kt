package com.min.mockstock.api.dto.request.coin

data class CoinBuyRequest(
    val market: String,
    val buyPrice: Double,
    val buyQuantity: Int
)