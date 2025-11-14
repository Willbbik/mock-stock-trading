package com.min.mockstock.infra.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.min.mockstock.common.kafka.KafkaTopic
import com.min.mockstock.infra.koreatrade.dto.StockPriceResponse
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class KoreaTradeStockPriceConsumer(
    private val redisTemplate: StringRedisTemplate,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = arrayOf(KafkaTopic.KOREA_TRADE_STOCK_PRICE))
    fun consume(@Payload stockPrice: String, @Header(KafkaHeaders.RECEIVED_KEY) key: String) {

        val redisKey = "STOCK:$key"
        val ops = redisTemplate.opsForValue()
        val originPrice = ops.get(redisKey)

        if (originPrice == null) {
            ops.set(redisKey, stockPrice)
            logger.info("KoreaTradeStockPriceConsumer create stock price - [Initial] key: $key, message: $stockPrice")
            return
        }

        // 🔥 값이 완전 동일하면 → 웹소켓 송신 X
        if (originPrice == stockPrice) {
            return
        }

        // 🔥 값이 다르면 → 웹소켓 송신 + Redis 업데이트
//        websocketSender.broadcast(json)

        ops.set(redisKey, stockPrice)
        logger.info("KoreaTradeStockPriceConsumer update stock price- key: $key, newPrice: ${stockPrice}")
    }


}