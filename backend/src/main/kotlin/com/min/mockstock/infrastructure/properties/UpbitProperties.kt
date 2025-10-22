package com.min.mockstock.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "upbit-config")
data class UpbitProperties (
    val baseUrl: String
)