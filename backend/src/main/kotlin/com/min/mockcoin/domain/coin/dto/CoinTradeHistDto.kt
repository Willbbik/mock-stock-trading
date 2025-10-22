package com.min.mockcoin.domain.coin.dto

data class CoinTradeHistDto(
    val market: String,
    val buyPrice: Double,
    val buyQuantity: Int
)