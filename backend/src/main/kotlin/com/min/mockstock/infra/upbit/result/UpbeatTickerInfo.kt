package com.min.mockstock.infra.upbit.result

import com.fasterxml.jackson.annotation.JsonAlias

data class UpbeatTickerInfo(
        @JsonAlias("market")
        val market: String,         // 종목 구분 코드

        @JsonAlias("trade_price")
        val tradePrice: Double,     // 종가(현재가)

        @JsonAlias("change")
        val change: String,         // 변화 상태

        @JsonAlias("change_price")
        val changePrice: String,    // 변화액의 절대값

        @JsonAlias("change_rate")
        val changeRate: String,     // 변화율의 절대값
)
