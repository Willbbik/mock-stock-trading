package com.min.mockstock.infra.redis.repository

import com.min.mockstock.domain.upbit.model.Market
import com.min.mockstock.domain.upbit.MarketType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UpbitMarketRepositoryRedis : CrudRepository<Market, MarketType>{
}