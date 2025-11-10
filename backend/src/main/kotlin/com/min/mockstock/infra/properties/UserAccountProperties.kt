package com.min.mockstock.infra.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import java.math.BigDecimal

@ConfigurationProperties(prefix = "user-account")
data class UserAccountProperties(
    val defaultBalance: BigDecimal
)