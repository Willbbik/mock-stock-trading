package com.min.mockstock.domain.upbit.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class UpbitTicker(

        val code: String?,

        val market: String?,

        @JsonAlias("trade_date")
        val tradeDate: String?,

        @JsonAlias("trade_time")
        val tradeTime: String?,

        @JsonAlias("trade_date_kst")
        val tradeDateKst: String?,

        @JsonAlias("trade_time_kst")
        val tradeTimeKst: String?,

        @JsonAlias("trade_timestamp")
        val tradeTimestamp: Long?,

        @JsonAlias("opening_price")
        val openingPrice: Double?,

        @JsonAlias("high_price")
        val highPrice: Double?,

        @JsonAlias("low_price")
        val lowPrice: Double?,

        @JsonAlias("trade_price")
        val tradePrice: Double?,

        @JsonAlias("prev_closing_price")
        val prevClosingPrice: Double?,

        val change: String?,

        @JsonAlias("change_price")
        val changePrice: Double?,

        @JsonAlias("change_rate")
        val changeRate: Double?,

        @JsonAlias("signed_change_price")
        val signedChangePrice: Double?,

        @JsonAlias("signed_change_rate")
        val signedChangeRate: Double?,

        @JsonAlias("trade_volume")
        val tradeVolume: Double?,

        @JsonAlias("acc_trade_price")
        val accTradePrice: Double?,

        @JsonAlias("acc_trade_price_24h")
        val accTradePrice24h: Double?,

        @JsonAlias("acc_trade_volume")
        val accTradeVolume: Double?,

        @JsonAlias("acc_trade_volume_24h")
        val accTradeVolume24h: Double?,

        @JsonAlias("highest_52_week_price")
        val highest52WeekPrice: Double?,

        @JsonAlias("highest_52_week_date")
        val highest52WeekDate: String?,

        @JsonAlias("lowest_52_week_price")
        val lowest52WeekPrice: Double?,

        @JsonAlias("lowest_52_week_date")
        val lowest52WeekDate: String?,

        val timestamp: Long?
)
