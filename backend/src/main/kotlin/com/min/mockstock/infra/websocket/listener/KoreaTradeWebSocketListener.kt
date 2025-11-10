package com.min.mockstock.infrastructure.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.min.mockstock.domain.shared.Stocks
import com.min.mockstock.infra.properties.KoreaTradeProperties
import com.min.mockstock.infra.websocket.dto.KoreaTradeWebSocketRequest
import okhttp3.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class KoreaTradeWebSocketListener(
    private val objectMapper: ObjectMapper,
    private val koreaTradeProperties: KoreaTradeProperties,
    private val koreaTradeClient: OkHttpClient
) : WebSocketListener() {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private var retryCount = 0
    private val maxRetries = 5

    override fun onOpen(webSocket: WebSocket, response: Response) {
        logger.info("Korea Trade WebSocket connection opened")

        Stocks.list.forEachIndexed { index, it ->
            val message = createSubscribeMessage(it.stockCode)
            val sendResult = webSocket.send(message)
            logger.info("Sent subscription message ${index + 1}, code = ${it.stockCode}, send result = $sendResult, result = $response")
        }
    }

    override fun onMessage(webSocket: WebSocket, bytes: okio.ByteString) {
        try {
            val message = bytes.utf8()
            logger.info("Received message: $message")
        } catch (e: Exception) {
            logger.info("Error processing WebSocket message", e)
        }
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        logger.info("WebSocket connection failed: ${t.message}", t)
        attemptReconnect()
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        logger.info("WebSocket connection closing: $code - $reason")
//        attemptReconnect()
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        logger.info("WebSocket connection closed: $code - $reason")
        attemptReconnect()
    }

    private fun createSubscribeMessage(stockCode: String): String {
        val request = KoreaTradeWebSocketRequest(
            header = KoreaTradeWebSocketRequest.Header(
                appkey = koreaTradeProperties.realAppKey,
                appsecret = koreaTradeProperties.realAppSecret,
                custtype = "P",
                tr_type = "1",
                content_type = "utf-8",
            ),
            body = KoreaTradeWebSocketRequest.Body(
                input = KoreaTradeWebSocketRequest.Input(
                    tr_id = "H0STCNT0",
                    tr_key = stockCode
                )
            )
        )

        return objectMapper.writeValueAsString(request)
    }

    private fun attemptReconnect() {
        if(retryCount > maxRetries) {
            logger.info("Max reconnection attempts reached. Giving up.")
            return;
        }

        retryCount++
        logger.info("Attempting to reconnect WebSocket...")

        try {
            TimeUnit.SECONDS.sleep(5) // Wait for 5 seconds before retrying
            val request = Request.Builder()
                .url(koreaTradeProperties.realWebsocketDomain)
                .build()

            koreaTradeClient.newWebSocket(request, this) // Create a new WebSocket connection
        } catch (e: InterruptedException) {
            logger.error("Reconnect attempt interrupted", e)
        }

    }
}