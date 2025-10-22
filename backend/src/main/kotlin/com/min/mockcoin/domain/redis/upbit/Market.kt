package com.min.mockcoin.domain.redis.upbit

import com.min.mockcoin.domain.shared.MarketType
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("market")
data class Market(
        @Id
        val marketType: MarketType,
        val markets: String
)
