package com.min.mockstock.application.query

import com.min.mockstock.domain.shared.StockInfo
import com.min.mockstock.domain.shared.Stocks
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class StockQueryService(
    private val redisTemplate: StringRedisTemplate,
) {

    fun getStocks() : List<StockInfo> {
        val result = Stocks.list.map {
            val redisKey = "STOCK:${it.stockCode}"
            val price = redisTemplate.opsForValue().get(redisKey)
            StockInfo(
                name = it.name,
                stockCode = it.stockCode,
                price = price?.toInt() ?: 0
            )
        }

        return result
    }

}