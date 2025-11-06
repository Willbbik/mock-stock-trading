package com.min.mockstock.infra.websocket.client

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
        .readTimeout(0, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    @PostConstruct
    fun connect() {
        try {
//            cacheApprovalKey()
            initializeWebSocket()
        } catch (e: Exception) {
            logger.error("Failed to initialize Korea Trade WebSocket connection", e)
        }
    }

    private fun initializeWebSocket() {
        val request = Request.Builder()
            .url(koreaTradeProperties.realWebsocketDomain)
            .build()

        val webSocket = client.newWebSocket(request, koreaTradeWebSocketListener)

        // Keep the connection alive
//        webSocket.dispatcher.executorService.shutdown()
    }

    // 한국 투자증권 웹소켓 연결을 위한 키 발급 후 레디스 캐시에 저장
    private fun cacheApprovalKey() {
        try {
            val approvalKey = requestApprovalKey()
            redisStringService.save("approvalKey", approvalKey)
            logger.info("Successfully cached approval key")
        } catch (e: Exception) {
            logger.error("Failed to cache approval key: ${e.message}", e)
            throw IllegalStateException("Failed to obtain approval key", e)
        }
    }

    // 웹소켓 연결 키 발급 요청
    private fun requestApprovalKey(): String {
        val request = createApprovalRequest()
        
        return client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                val errorBody = response.body?.string() ?: "No error body"
                throw IOException("Failed to get approval key. Status: ${response.code}, Body: $errorBody")
            }
            
            response.body?.use { responseBody ->
                parseApprovalKeyResponse(responseBody.string())
            } ?: throw IOException("Empty response body when requesting approval key")
        }
    }

    // 웹소켓 연결 키 발급 요청 바디 생성 및 호출
    private fun createApprovalRequest(): Request {
        val body = mapOf(
            "grant_type" to "client_credentials",
            "appkey" to koreaTradeProperties.realAppKey,
            "secretkey" to koreaTradeProperties.realAppSecret
        )
        val requestBody = objectMapper.writeValueAsString(body)
            .toRequestBody("application/json; charset=utf-8".toMediaType())
            
        return Request.Builder()
            .url("${koreaTradeProperties.realDomain}/oauth2/Approval")
            .post(requestBody)
            .build()
    }

    // 웹소켓 연결 키 발급 응답 파싱
    private fun parseApprovalKeyResponse(responseBody: String): String {
        return try {
            val responseMap = objectMapper.readValue<Map<String, String>>(responseBody)
            responseMap["approval_key"] ?: throw IllegalStateException("No approval_key in response")
        } catch (e: Exception) {
            throw IOException("Failed to parse approval response: ${e.message}", e)
        }
    }
}