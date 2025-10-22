package com.min.mockstock.domain.redis.upbit

import com.min.mockstock.domain.shared.MarketType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UpbitMarketRepositoryRedis : CrudRepository<Market, MarketType>{
}