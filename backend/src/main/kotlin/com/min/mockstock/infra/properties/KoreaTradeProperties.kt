package com.min.mockstock.infra.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "korea-trade-open-api")
data class KoreaTradeProperties(
        val realAppKey: String,
        val realAppSecret: String,
        val mockAppKey: String,
        val mockAppSecret: String,
        val realDomain: String,
        val realWebsocketDomain: String,
        val mockDomain: String,
        val mockWebsocketDomain: String
)