package com.min.mockstock.infra.koreatrade

import com.min.mockstock.domain.shared.StockInfo
import com.min.mockstock.domain.shared.Stocks
import com.min.mockstock.infra.properties.KoreaTradeProperties
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service

@Service
class Test(
    val koreaTradeProperties: KoreaTradeProperties
) {
    val client = OkHttpClient()
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    val stocks: List<StockInfo> = Stocks.list

    @PostConstruct
    fun onStart() {
        println("✅ 서버 시작 - 주식 시세 스케줄러 실행")
        start()
    }

    @PreDestroy
    fun onStop() {
        println("🛑 서버 종료 - 스케줄러 중단")
        stop()
    }


    fun start() {
        scope.launch {
            while(isActive) {
                try {

                    stocks.chunked(20).forEachIndexed { idx, batch ->
                        println("🚀 [Batch ${idx + 1}] ${batch.size}개 종목 조회 시작")

                        val responses = batch.map { stock ->
                            async {
                                val json = fetchStock(stock.stockCode)
                                println("Fetched ${stock.stockCode}: $json")
                            }
                        }.awaitAll()

                        delay(1000) // 1초 간격
                    }

                } catch (e: Exception) {
                    println("Error occurred: ${e.message}. Continuing...")
                }
            }
        }
    }

    private fun fetchStock(stock: String): String {
        val url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price?FID_COND_MRKT_DIV_CODE=J&FID_INPUT_ISCD=$stock"
        val request = Request.Builder()
            .url(url)
            .addHeader("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6ImU1MDk2MmFhLTk2NzItNDhhNS04YzI4LWJmZGI2MzlhMzBjZSIsInByZHRfY2QiOiIiLCJpc3MiOiJ1bm9ndyIsImV4cCI6MTc2Mjg1MTc4NiwiaWF0IjoxNzYyNzY1Mzg2LCJqdGkiOiJQUzAwT1k4YTJaQXYwVHV1SlVHWFIzVzhhQjJoMmR0OVk3VFEifQ.d4Dg5lFP79DpIzJ15fbuywGglArM74fk9XsxkS2IkfJWcBMBlodvw5VI66AmU-Bto_mZPazPZXYvJ-p4iG-hQg")
            .addHeader("appkey", koreaTradeProperties.realAppKey)
            .addHeader("appsecret", koreaTradeProperties.realAppSecret)
            .addHeader("custtype", "P")
            .addHeader("content-type", "application/json; charset=utf-8")
            .addHeader("tr_id", "FHPST01010000")
            .build()

        client.newCall(request).execute().use { res ->
            return res.body?.string() ?: "{}"
        }
    }

    fun stop() {
        println("🛑 StockPriceScheduler 중단")
        scope.cancel()
    }


}