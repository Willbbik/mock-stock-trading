package com.min.mockstock.infra.websocket.client

import com.min.mockstock.infra.properties.KoreaTradeProperties
import com.min.mockstock.infrastructure.websocket.KoreaTradeWebSocketListener
import jakarta.annotation.PostConstruct
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class KoreaTradeWebSocketClient(
    private val koreaTradeProperties: KoreaTradeProperties,
    private val koreaTradeWebSocketListener: KoreaTradeWebSocketListener,
    private val koreaTradeClient: OkHttpClient
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

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
            .url(koreaTradeProperties.realWebsocketDomain)
            .build()

        koreaTradeClient.newWebSocket(request, koreaTradeWebSocketListener)

        // Keep the connection alive
//        webSocket.dispatcher.executorService.shutdown()
    }

}