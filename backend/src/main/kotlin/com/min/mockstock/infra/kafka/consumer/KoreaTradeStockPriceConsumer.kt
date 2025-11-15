package com.min.mockstock.infra.kafka.consumer

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
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
    private val objectMapper = ObjectMapper()
        .registerKotlinModule()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @KafkaListener(topics = arrayOf(KafkaTopic.KOREA_TRADE_STOCK_PRICE))
    fun consume(@Payload json: String, @Header(KafkaHeaders.RECEIVED_KEY) stockCode: String) {
        val redisKey = "STOCK:$stockCode"
        val message = objectMapper.readValue<StockPriceResponse>(json, StockPriceResponse::class.java)
        val ops = redisTemplate.opsForValue()
        val originPrice = ops.get(redisKey)
        val newPrice = message.output.stockPresentPrice

        if (originPrice == null) {
            ops.set(redisKey, newPrice)
            logger.info("KoreaTradeStockPriceConsumer create stock price - [Initial] stockCode: $stockCode, message: $message")
            return
        }

        // 🔥 값이 완전 동일하면 → 웹소켓 송신 X
        if (originPrice == newPrice) {
            logger.info("KoreaTradeStockPriceConsumer no update stock price- stockCode: $stockCode, newPrice: $newPrice")
            return
        }

        ops.set(redisKey, newPrice)
        sendToWebsocket(stockCode, newPrice)
        logger.info("KoreaTradeStockPriceConsumer update stock price- stockCode: $stockCode, newPrice: ${newPrice}")
    }


    fun sendToWebsocket(stockCode: String, newPrice: String) {
        // TODO. 웹소켓으로 데이터 전송하는 로직 구현

        val param = mapOf(
            "stockCode" to stockCode,
            "data" to mapOf(
                "price" to newPrice.toInt()
            )
        )

        val subscribeMsg = gson.toJson(param)
        messagingTemplate.convertAndSend("/topic/test01", subscribeMsg)
    }


}