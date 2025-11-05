package com.min.mockstock.infra.websocket.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.min.mockstock.infrastructure.websocket.KoreaTradeWebSocketListener
import com.min.mockstock.infrastructure.websocket.KoreaTradeWebSocketProperties
import jakarta.annotation.PostConstruct
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class KoreaTradeWebSocketClient(
    private val objectMapper: ObjectMapper,
    private val webSocketProperties: KoreaTradeWebSocketProperties
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val client = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    @PostConstruct
    fun connect() {
        try {
            initializeWebSocket()
        } catch (e: Exception) {
            logger.error("Failed to initialize Korea Trade WebSocket connection", e)
        }
    }

    private fun initializeWebSocket() {
        val request = Request.Builder()
            .url(webSocketProperties.url)
            .build()

        val listener = KoreaTradeWebSocketListener(objectMapper, webSocketProperties)
        client.newWebSocket(request, listener)

        // Keep the connection alive
        client.dispatcher.executorService.shutdown()
    }
}