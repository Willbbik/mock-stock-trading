package com.min.mockstock.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "korea-trade-open-api")
data class KoreaTradeProperties(
        val appKey: String,
        val appSecret: String,
        val realDomain: String,
        val realWebsocketDomain: String,
        val mockDomain: String,
        val mockWebsocketDomain: String
)