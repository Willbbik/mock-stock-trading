package com.min.mockstock.controller

import com.min.mockstock.domain.coin.CoinTradeHist
import com.min.mockstock.domain.coin.dto.CoinTradeHistDto
import com.min.mockstock.domain.coin.dto.Top3TrendingCoinResponse
import com.min.mockstock.service.CoinService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CoinController(
    val coinService: CoinService
) {

    @PostMapping("/api/coin/buy")
    fun buyCoin(@RequestBody dto: CoinTradeHistDto): ResponseEntity<String> {
        coinService.buyCoin(dto)
        return ResponseEntity.ok("success")
    }

    @GetMapping("/api/coin/top3/trending")
    fun getTop3TrendingCoins(): ResponseEntity<List<Top3TrendingCoinResponse>> {
        val top3TrendingCoins = coinService.getTop3TrendingCoins()
        return ResponseEntity.ok(top3TrendingCoins)
    }

    @GetMapping("/api/coin/test")
    fun getTest(): ResponseEntity<List<CoinTradeHist>> {
        return ResponseEntity.ok(coinService.getTest())
    }

}