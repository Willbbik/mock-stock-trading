package com.min.mockstock.infra.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "user-account")
data class UserAccountProperties(
    val defaultBalance: Double
)