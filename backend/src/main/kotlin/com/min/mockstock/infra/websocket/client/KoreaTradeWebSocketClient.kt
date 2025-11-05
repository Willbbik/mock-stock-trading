package com.min.mockstock.infra.websocket.client

//import com.min.mockstock.infrastructure.websocket.KoreaTradeWebSocketProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.min.mockstock.infra.properties.KoreaTradeProperties
import com.min.mockstock.infra.redis.RedisStringService
import com.min.mockstock.infrastructure.websocket.KoreaTradeWebSocketListener
import jakarta.annotation.PostConstruct
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.concurrent.TimeUnit

@Component
class KoreaTradeWebSocketClient(
    private val objectMapper: ObjectMapper,
    private val koreaTradeProperties: KoreaTradeProperties,
    private val koreaTradeWebSocketListener: KoreaTradeWebSocketListener,
    private val redisStringService: RedisStringService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val client = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    @PostConstruct
    fun connect() {
        try {
            cacheApprovalKey()
            initializeWebSocket()
        } catch (e: Exception) {
            logger.error("Failed to initialize Korea Trade WebSocket connection", e)
        }
    }

    private fun initializeWebSocket() {
        val request = Request.Builder()
            .url(koreaTradeProperties.mockDomain)
            .build()

//        val listener = KoreaTradeWebSocketListener(objectMapper, koreaTradeProperties)
        client.newWebSocket(request, koreaTradeWebSocketListener)

        // Keep the connection alive
        client.dispatcher.executorService.shutdown()
    }

    private fun cacheApprovalKey() {
        val body = mapOf(
            "grant_type" to "client_credentials",
            "appkey" to koreaTradeProperties.mockAppKey,
            "secretkey" to koreaTradeProperties.mockAppSecret
        )

        val jsonBody = objectMapper.writeValueAsString(body)
        val requestBody = jsonBody.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url(koreaTradeProperties.mockDomain + "/oauth2/Approval")
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val responseBody = response.body!!.string()
            val readValue = objectMapper.readValue<Map<String, String>>(responseBody)
            val approvalKey = readValue["approval_key"]
            redisStringService.save("approvalKey", approvalKey ?: "")
        }
    }
}