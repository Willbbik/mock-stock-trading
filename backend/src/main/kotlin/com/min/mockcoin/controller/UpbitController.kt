package com.min.mockcoin.controller

import com.min.mockcoin.domain.upbit.dto.MarketInfoRequest
import com.min.mockcoin.infrastructure.upbit.result.UpbeatTickerInfo
import com.min.mockcoin.service.UpbitService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UpbitController(
        private val upbitService: UpbitService
) { 

    @GetMapping("/upbit/markets")
    fun getMarkets(request: MarketInfoRequest): List<UpbeatTickerInfo>? {
        return upbitService.getMarkets(request)
    }
}