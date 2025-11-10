package com.min.mockstock.api.controller

import com.min.mockstock.application.command.TradeCommandService
import com.min.mockstock.domain.upbit.dto.MarketInfoRequest
import com.min.mockstock.infra.upbit.result.UpbeatTickerInfo
import com.min.mockstock.application.service.UpbitService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TradeController(
    private val tradeCommandService: TradeCommandService,
) {

    @PostMapping("/api/trade/order")
    fun order() {
        tradeCommandService.order()
    }


//    @GetMapping("/api/trade/markets")
//    fun getMarkets(request: MarketInfoRequest): List<UpbeatTickerInfo>? {
//        return upbitService.getMarkets(request)
//    }


}