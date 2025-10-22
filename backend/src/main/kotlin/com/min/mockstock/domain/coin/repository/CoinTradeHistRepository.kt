package com.min.mockstock.domain.coin.repository

import com.min.mockstock.domain.coin.CoinTradeHist
import org.springframework.data.jpa.repository.JpaRepository

interface CoinTradeHistRepository : JpaRepository<CoinTradeHist, Long> {

    fun findTop10ByOrderByIdDesc(): List<CoinTradeHist>

}