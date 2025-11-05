package com.min.mockstock.application.scheduler

import com.fasterxml.jackson.databind.ObjectMapper
import com.min.mockstock.infra.redis.repository.UpbitMarketRepositoryRedis
import com.min.mockstock.infra.upbit.UpbitManager
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UpbitScheduler(
    private val upbitManager: UpbitManager,
    private val upbitMarketRepositoryRedis: UpbitMarketRepositoryRedis,
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    /**
     * 5분 주기로 마켓(코인) 정보 업데이트
     *
     * 원화, BTC, USDT 구분해서 저장
     */
//    @Scheduled(fixedRate = 1000 * 60 * 5)
//    fun updateMarkets() {
//        val markets = upbitManager.getMarkets()
//
//        val marketGroup = markets.groupBy {
//            it.market?.substringBefore("-")
//        }
//
//        val krw = marketGroup.get(MarketType.KRW.name)
//                ?.mapNotNull { it.market }
//                ?.joinToString(", ")
//                .toString()
//
//        val btc = marketGroup.get(MarketType.BTC.name)
//                ?.mapNotNull { it.market }
//                ?.joinToString(", ")
//                .toString()
//
//        val usdt = marketGroup.get(MarketType.USDT.name)
//                ?.mapNotNull { it.market }
//                ?.joinToString(", ")
//                .toString()
//
//        upbitMarketRepositoryRedis.save(Market(MarketType.KRW, krw))
//        upbitMarketRepositoryRedis.save(Market(MarketType.BTC, btc))
//        upbitMarketRepositoryRedis.save(Market(MarketType.USDT, usdt))
//    }


    /**
     * 0.5초 단위로 원화 코인 가격 정보 업데이트
     */
//    @Scheduled(fixedRate = 500)
//    fun updateKrwCoin() {
//        // 저장된 원화 코인 정보 조회
//        val market = upbitMarketRepositoryRedis.findById(MarketType.KRW)
//
//        // todo list가 아니라 map 형태로 바꿔서 넘겨야 화면에서 노출할 때 좋음
//        // todo 가격이 바뀐 코인만 내려주도록 로직 추가 필요
//
//        /**
//         *
//         * 1. 0.5초마다 레디스에 각 코인 가격 계속 업데이트
//         *
//         * 2. 이전 값이랑 현재 값이랑 비교
//         *
//         * 3. 이전 값이랑 다르면 kafka로 해당 코인 가격 정보 전송
//         *
//         */
//
//
//        // 원화 코인들 정보 조회 (신규 코인 가격 데이터)
//        val marketInfos:List<UpbeatTickerInfo> = upbitManager.getMarketInfoByType(market.get().markets)
//        val changedData = mutableMapOf<String, UpbeatTickerInfo>()
//
//        // todo 코인 데이터를 redis, mongo 어디에 넣어놓지?
//        marketInfos.forEach { info ->
//            upbitMarketRepositoryRedis.findById(MarketType.KRW)
//        }
//
//
//        val marketMap:Map<String, UpbeatTickerInfo> = marketInfos.associateBy { it.market }
//
//
//
////        kafkaTemplate.send("test", objectMapper.writeValueAsString(marketMap))
//    }

    /**
     * 0.5초 단위로 BTC 코인 가격 정보 업데이트
     */


    /**
     * 0.5초 단위로 USDT 코인 가격 정보 업데이트
     */



}