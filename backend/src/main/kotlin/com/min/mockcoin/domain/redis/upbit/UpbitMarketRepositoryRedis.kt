package com.min.mockcoin.domain.redis.upbit

import com.min.mockcoin.domain.shared.MarketType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UpbitMarketRepositoryRedis : CrudRepository<Market, MarketType>{
}