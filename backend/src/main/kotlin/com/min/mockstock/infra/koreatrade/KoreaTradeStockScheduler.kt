package com.min.mockstock.infra.koreatrade

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.min.mockstock.common.kafka.KafkaTopic
import com.min.mockstock.domain.shared.StockInfo
import com.min.mockstock.domain.shared.Stocks
import com.min.mockstock.infra.koreatrade.dto.StockPriceResponse
import com.min.mockstock.infra.properties.KoreaTradeProperties
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class KoreaTradeStockScheduler(
    private val koreaTradeProperties: KoreaTradeProperties,
    private val redisTemplate: StringRedisTemplate,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private val client = OkHttpClient()
    private val objectMapper = ObjectMapper()
        .registerKotlinModule()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val stocks: List<StockInfo> = Stocks.list
    private val tokenMutex = Mutex() // 토큰 갱신용 락

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
            while (isActive) {
                try {
                    stocks.chunked(20).forEachIndexed { idx, batch ->
                        println("🚀 [Batch ${idx + 1}] ${batch.size}개 종목 조회 시작")

                        batch.map { stock ->
                            async {
                                val json = fetchStock(stock.stockCode)
                            }
                        }.awaitAll()

                        delay(1000) // 1초 간격
                    }

                } catch (e: Exception) {
                    logger.debug("Error occurred: ${e.message}. Continuing...")
                }
            }
        }
    }

    fun stop() {
        scope.cancel()
    }

    private suspend fun fetchStock(stock: String): String {
        val url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price?FID_COND_MRKT_DIV_CODE=J&FID_INPUT_ISCD=$stock"
        val token = getAuthToken()
        val request = Request.Builder()
            .url(url)
            .addHeader("authorization", "Bearer $token")
            .addHeader("appkey", koreaTradeProperties.realAppKey)
            .addHeader("appsecret", koreaTradeProperties.realAppSecret)
            .addHeader("custtype", "P")
            .addHeader("content-type", "application/json; charset=utf-8")
            .addHeader("tr_id", "FHPST01010000")
            .build()

        client.newCall(request).execute().use { res ->

            // 토큰 만료 시 재발급 후 재시도
            if (res.code == 403) {
                val newToken = getAuthToken() ?: return "{}"
                val retryReq = request.newBuilder()
                    .removeHeader("authorization")
                    .addHeader("authorization", "Bearer $newToken")
                    .build()

                client.newCall(retryReq).execute().use { retryRes ->
                    val body = retryRes.body?.string() ?: "{}"
                    sendStockData(stock, body)
                    return body
                }
            }

            val body = res.body?.string() ?: "{}"
            sendStockData(stock, body)
            return body
        }
    }

    private fun sendStockData(stockCode: String, json: String) {

        try {
            val result = objectMapper.readValue<StockPriceResponse>(json, StockPriceResponse::class.java)
            kafkaTemplate.send(KafkaTopic.KOREA_TRADE_STOCK_PRICE, stockCode, result.output?.stockPresentPrice)
        } catch (e: Exception) {
            logger.debug("Error parsing stock data for $stockCode: ${e.message}")
            return
        }
    }

    suspend fun getAuthToken(): String? {
        val redisTokenKey = "access_token"
        val ops = redisTemplate.opsForValue()

        tokenMutex.withLock {
            // 락 획득 후 다시 한 번 체크
            ops.get(redisTokenKey)?.let {
                return it
            }

            // 토큰 갱신
            val newToken = fetchAuthToken() ?: return null

            logger.info("한국투자증권 Token Redis 저장")
            ops.set(redisTokenKey, newToken, Duration.ofHours(10))
            return newToken
        }
    }

    fun fetchAuthToken(): String? {
        val body = mapOf(
            "grant_type" to "client_credentials",
            "appkey" to koreaTradeProperties.realAppKey,
            "appsecret" to koreaTradeProperties.realAppSecret,
        )

        val request = Request.Builder()
            .url("https://openapivts.koreainvestment.com:29443/oauth2/tokenP")
            .post(objectMapper.writeValueAsString(body).toRequestBody())
            .build()

        logger.info("한국투자증권 Token 발급 요청")

        try {
            client.newCall(request).execute().use { res ->
                if (!res.isSuccessful) {
                    logger.info("Failed to get auth token: ${res.code} - ${res.message}")
                    return null
                }

                val json = res.body?.string() ?: return null
                val map: Map<String, Any> = objectMapper.readValue(json)
                val token = map["access_token"]?.toString()
                return token
            }
        } catch (e: Exception) {
            logger.info("Exception while getting auth token: ${e.message}")
            return null
        }
    }


}