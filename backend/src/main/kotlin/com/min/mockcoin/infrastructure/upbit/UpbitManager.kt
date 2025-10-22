package com.min.mockcoin.infrastructure.upbit

import com.min.mockcoin.infrastructure.upbit.result.UpbeatTickerInfo
import com.min.mockcoin.infrastructure.upbit.result.UpbitMarketInfo
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class UpbitManager(
        @Qualifier("upbitWebClient") private val upbitClient: WebClient

) {

    fun getMarkets(): List<UpbitMarketInfo> {
        return upbitClient.get()
                .uri("v1/market/all")
                .retrieve()
                .bodyToMono<List<UpbitMarketInfo>>()
                .block()
                .orEmpty()
    }

    fun getMarketInfoByType(markets: String): List<UpbeatTickerInfo> {
        return upbitClient.get()
                .uri {
                    it.path("/v1/ticker")
                    .queryParam("markets", markets)
                    .build()
                }
                .retrieve()
                .bodyToMono<List<UpbeatTickerInfo>>()
                .block()
                .orEmpty()
    }


}