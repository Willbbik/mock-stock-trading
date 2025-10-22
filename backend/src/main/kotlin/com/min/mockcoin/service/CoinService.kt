package com.min.mockcoin.service

import com.min.mockcoin.domain.coin.CoinTradeHist
import com.min.mockcoin.domain.coin.dto.CoinTradeHistDto
import com.min.mockcoin.domain.coin.dto.Top3TrendingCoinResponse

interface CoinService {

    fun buyCoin(coinTradeHistDto: CoinTradeHistDto)

    fun getTop3TrendingCoins(): List<Top3TrendingCoinResponse>

    fun getTest() : List<CoinTradeHist>

}