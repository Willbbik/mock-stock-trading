package com.min.mockstock.domain.shared

data class StockInfo(
        var name: String,
        var stockCode: String,
        var price: String = "0"
)