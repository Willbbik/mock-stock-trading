package com.min.mockstock.domain.upbit.model

data class DebeziumPayload(
    val before: MarketOrderEvent?,
    val after: MarketOrderEvent?,
    val op: String?,
)
