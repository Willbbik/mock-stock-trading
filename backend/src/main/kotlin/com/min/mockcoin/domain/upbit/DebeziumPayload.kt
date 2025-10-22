package com.min.mockcoin.domain.upbit

data class DebeziumPayload(
        val before: MarketOrderEvent?,
        val after: MarketOrderEvent?,
        val op: String?,
)
