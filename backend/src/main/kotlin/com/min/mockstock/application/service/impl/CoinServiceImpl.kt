
package com.min.mockstock.application.service.impl

import com.min.mockstock.domain.coin.repository.CoinTradeHistRepository
import com.min.mockstock.application.service.CoinService
import com.min.mockstock.domain.coin.model.CoinTradeHist
import com.min.mockstock.api.dto.request.coin.CoinBuyRequest
import com.min.mockstock.api.dto.response.coin.TrendingTop3CoinResponse
import jakarta.transaction.Transactional
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service

@Service
class CoinServiceImpl(
    val coinTradeHistRepository: CoinTradeHistRepository,
    val redisTemplate: RedisTemplate<String, String>
) : CoinService {

    @Transactional
    override fun buyCoin(coinBuyRequest: CoinBuyRequest) {
        val coinTradeHist = CoinTradeHist(
            market = coinBuyRequest.market,
            buyPrice = coinBuyRequest.buyPrice,
            buyQuantity = coinBuyRequest.buyQuantity,
            trdType = "A"
        )

        coinTradeHistRepository.save(coinTradeHist)
    }

    override fun getTop3TrendingCoins(): List<TrendingTop3CoinResponse> {
        val top3 = redisTemplate.opsForZSet().reverseRangeWithScores("coin_trades", 0, 2)

        return top3?.map {
            TrendingTop3CoinResponse(
                market = it.value,
                count = it.score?.toLong()
            )
        } ?: emptyList()
    }


//    @Cacheable("testCoinTrades")
    override fun getTest(): List<CoinTradeHist> {
        return coinTradeHistRepository.findTop10ByOrderByIdDesc()
    }


    @KafkaListener(topics = arrayOf("COIN_TRADES"))
    fun consume(@Header(KafkaHeaders.RECEIVED_KEY) key: String, message: String) {
        incrementCoinTrade(key)
    }

    fun incrementCoinTrade(market: String) {
        redisTemplate.opsForZSet().incrementScore("coin_trades", market, 1.0)
    }

}