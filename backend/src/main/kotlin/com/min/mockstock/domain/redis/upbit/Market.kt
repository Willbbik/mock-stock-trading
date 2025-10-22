package com.min.mockstock.domain.redis.upbit

import com.min.mockstock.domain.shared.MarketType
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("market")
data class Market(
        @Id
        val marketType: MarketType,
        val markets: String
)
