package com.min.mockstock.service

import com.min.mockstock.domain.coin.CoinTradeHist
import com.min.mockstock.domain.coin.dto.CoinTradeHistDto
import com.min.mockstock.domain.coin.dto.Top3TrendingCoinResponse

interface CoinService {

    fun buyCoin(coinTradeHistDto: CoinTradeHistDto)

    fun getTop3TrendingCoins(): List<Top3TrendingCoinResponse>

    fun getTest() : List<CoinTradeHist>

}