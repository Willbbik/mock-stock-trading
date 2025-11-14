package com.min.mockstock.api.controller

import com.min.mockstock.application.query.StockQueryService
import com.min.mockstock.domain.shared.StockInfo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StockController(
    private val stockQueryService: StockQueryService
) {

    @GetMapping("/api/stocks")
    fun getStocks(): ResponseEntity<List<StockInfo>> {
        return ResponseEntity.ok(stockQueryService.getStocks())
    }

}