package com.min.mockstock.api.controller

import com.min.mockstock.domain.coin.model.CoinTradeHist
import com.min.mockstock.api.dto.request.coin.CoinBuyRequest
import com.min.mockstock.api.dto.response.coin.TrendingTop3CoinResponse
import com.min.mockstock.application.service.CoinService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CoinController(
    val coinService: CoinService
) {

    @PostMapping("/api/coin/buy")
    fun buyCoin(@RequestBody dto: CoinBuyRequest): ResponseEntity<String> {
        coinService.buyCoin(dto)
        return ResponseEntity.ok("success")
    }

    @GetMapping("/api/coin/top3/trending")
    fun getTop3TrendingCoins(): ResponseEntity<List<TrendingTop3CoinResponse>> {
        val top3TrendingCoins = coinService.getTop3TrendingCoins()
        return ResponseEntity.ok(top3TrendingCoins)
    }

    @GetMapping("/api/coin/test")
    fun getTest(): ResponseEntity<List<CoinTradeHist>> {
        return ResponseEntity.ok(coinService.getTest())
    }

}