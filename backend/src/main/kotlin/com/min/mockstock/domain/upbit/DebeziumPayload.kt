package com.min.mockstock.domain.upbit

data class DebeziumPayload(
        val before: MarketOrderEvent?,
        val after: MarketOrderEvent?,
        val op: String?,
)
