
package com.min.mockcoin.service.impl

import com.min.mockcoin.domain.coin.CoinTradeHist
import com.min.mockcoin.domain.coin.dto.CoinTradeHistDto
import com.min.mockcoin.domain.coin.dto.Top3TrendingCoinResponse
import com.min.mockcoin.domain.coin.repository.CoinTradeHistRepository
import com.min.mockcoin.service.CoinService
import jakarta.transaction.Transactional
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CoinServiceImpl(
    val coinTradeHistRepository: CoinTradeHistRepository,
    val redisTemplate: RedisTemplate<String, String>
) : CoinService {

    @Transactional
    override fun buyCoin(coinTradeHistDto: CoinTradeHistDto) {
        val coinTradeHist = CoinTradeHist(
            market = coinTradeHistDto.market,
            buyPrice = coinTradeHistDto.buyPrice,
            buyQuantity = coinTradeHistDto.buyQuantity,
            trdType = "A"
        )

        coinTradeHistRepository.save(coinTradeHist)
    }

    override fun getTop3TrendingCoins(): List<Top3TrendingCoinResponse> {
        val top3 = redisTemplate.opsForZSet().reverseRangeWithScores("coin_trades", 0, 2)

        return top3?.map {
            Top3TrendingCoinResponse(
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