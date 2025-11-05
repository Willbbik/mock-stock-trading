package com.min.mockstock.application.service

import com.min.mockstock.domain.coin.model.CoinTradeHist
import com.min.mockstock.api.dto.request.coin.CoinBuyRequest
import com.min.mockstock.api.dto.response.coin.TrendingTop3CoinResponse

interface CoinService {

    fun buyCoin(coinBuyRequest: CoinBuyRequest)

    fun getTop3TrendingCoins(): List<TrendingTop3CoinResponse>

    fun getTest() : List<CoinTradeHist>

}