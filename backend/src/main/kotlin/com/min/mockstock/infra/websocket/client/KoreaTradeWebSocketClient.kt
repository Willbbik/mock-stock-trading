package com.min.mockstock.infra.websocket.client

import com.min.mockstock.infra.properties.KoreaTradeProperties
import com.min.mockstock.infrastructure.websocket.KoreaTradeWebSocketListener
import jakarta.annotation.PostConstruct
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class KoreaTradeWebSocketClient(
    private val koreaTradeProperties: KoreaTradeProperties,
    private val koreaTradeWebSocketListener: KoreaTradeWebSocketListener,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val client = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .pingInterval(5, TimeUnit.SECONDS) // 5초마다 ping 전송해서 연결 유지
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
            .url(koreaTradeProperties.realWebsocketDomain)
            .build()

        val webSocket = client.newWebSocket(request, koreaTradeWebSocketListener)

        // Keep the connection alive
//        webSocket.dispatcher.executorService.shutdown()
    }

}