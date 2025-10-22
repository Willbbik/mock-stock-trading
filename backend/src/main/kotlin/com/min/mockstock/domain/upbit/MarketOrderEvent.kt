package com.min.mockstock.domain.upbit

data class MarketOrderEvent(
        val id:Long,
        val market:String,
        val price: Double,
        val create_dt: String
)
