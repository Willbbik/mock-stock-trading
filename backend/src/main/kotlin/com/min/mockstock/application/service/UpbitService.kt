package com.min.mockstock.application.service

import com.min.mockstock.domain.upbit.model.UpbitKafkaTopic
import com.min.mockstock.domain.upbit.model.UpbitMarkets
import com.min.mockstock.domain.upbit.dto.MarketInfoRequest
import com.min.mockstock.infra.upbit.UpbitManager
import com.min.mockstock.infra.upbit.result.UpbeatTickerInfo
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
@Slf4j
class UpbitService(
        private val upbitManager: UpbitManager,
        private val messagingTemplate: SimpMessageSendingOperations
) {

    private val log = LoggerFactory.getLogger(UpbitService::class.java)

    fun getMarkets(request: MarketInfoRequest): List<UpbeatTickerInfo>? {
        // TODO. 이러면 트래픽이 증가했을 때, 업비트 API 통신 횟수 제한으로 문제가 될 수 있음. 캐싱 or 개선 필요
        val markets = when (request.market) {
            "krw" -> upbitManager.getMarketInfoByType(UpbitMarkets.krwMarkets.joinToString(","))
            "btc" -> upbitManager.getMarketInfoByType(UpbitMarkets.btcMarkets.joinToString(","))
            "usdt" -> upbitManager.getMarketInfoByType(UpbitMarkets.usdtMarkets.joinToString(","))
            else -> emptyList()
        }

        return markets
    }

    @KafkaListener(topics = arrayOf(UpbitKafkaTopic.TICKER))
    fun consume(message: String) {
        messagingTemplate.convertAndSend("/topic/test01", message);
    }

//    @KafkaListener(topics = arrayOf("mysqlserver.test1.TB_MARKET_ORDER"))
//    fun consume2(message: String) {
////        println("커넥터로 데이터 받기 성공!!!! message = ${message}")
//    }

    @KafkaListener(topics = arrayOf("dlq_test_sink_03"))
    fun consume3(message: String) {
        println("DLQ 데이터 받기 성공!!!! message = ${message}")
    }

}