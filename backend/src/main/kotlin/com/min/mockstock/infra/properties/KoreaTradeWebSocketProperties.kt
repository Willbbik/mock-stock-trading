package com.min.mockstock.infrastructure.websocket

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "korea-trading.websocket")
data class KoreaTradeWebSocketProperties(
    val url: String,
    val approvalKey: String,
    val custType: String = "P",
    val trType: String = "1",
    val contentType: String = "utf-8",
    val trId: String = "H0IFCNT0"
)
