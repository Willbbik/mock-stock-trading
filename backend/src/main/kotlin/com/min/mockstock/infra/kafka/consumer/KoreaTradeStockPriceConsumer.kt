package com.min.mockstock.infra.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.min.mockstock.common.kafka.KafkaTopic
import com.min.mockstock.infra.koreatrade.dto.StockPriceResponse
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class KoreaTradeStockPriceConsumer(
    private val redisTemplate: StringRedisTemplate,
    private val messagingTemplate: SimpMessagingTemplate,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private val gson = Gson()

    @KafkaListener(topics = arrayOf(KafkaTopic.KOREA_TRADE_STOCK_PRICE))
    fun consume(@Payload stockPrice: String, @Header(KafkaHeaders.RECEIVED_KEY) stockCode: String) {

        val redisKey = "STOCK:$stockCode"
        val ops = redisTemplate.opsForValue()
        val originPrice = ops.get(redisKey)

        if (originPrice == null) {
            ops.set(redisKey, stockPrice)
            logger.info("KoreaTradeStockPriceConsumer create stock price - [Initial] stockCode: $stockCode, message: $stockPrice")
            return
        }

        // 🔥 값이 완전 동일하면 → 웹소켓 송신 X
        if (originPrice == stockPrice) {
            logger.info("KoreaTradeStockPriceConsumer no update stock price- stockCode: $stockCode, newPrice: ${stockPrice}")
            return
        }

        ops.set(redisKey, stockPrice)
        sendToWebsocket(stockCode, stockPrice)
        logger.info("KoreaTradeStockPriceConsumer update stock price- stockCode: $stockCode, newPrice: ${stockPrice}")
    }


    fun sendToWebsocket(stockCode: String, newPrice: String) {
        // TODO. 웹소켓으로 데이터 전송하는 로직 구현

        val param = mapOf(
            "stockCode" to stockCode,
            "data" to mapOf(
                "price" to newPrice
            )
        )

        val subscribeMsg = gson.toJson(param)
        messagingTemplate.convertAndSend("/topic/test01", subscribeMsg)
    }


}