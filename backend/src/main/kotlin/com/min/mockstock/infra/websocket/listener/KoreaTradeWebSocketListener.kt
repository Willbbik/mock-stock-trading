package com.min.mockstock.infrastructure.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.min.mockstock.domain.trade.model.Stocks
import com.min.mockstock.infra.properties.KoreaTradeProperties
import com.min.mockstock.infra.websocket.dto.KoreaTradeWebSocketRequest
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class KoreaTradeWebSocketListener(
    private val objectMapper: ObjectMapper,
    private val koreaTradeProperties: KoreaTradeProperties
) : WebSocketListener() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun onOpen(webSocket: WebSocket, response: Response) {
        logger.info("Korea Trade WebSocket connection opened")

        Stocks.list.forEachIndexed { index, it ->
            val message = createSubscribeMessage(it.code)
            val sendResult = webSocket.send(message)
            logger.info("Sent subscription message ${index + 1}, code = ${it.code}, send result = $sendResult, result = $response")
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
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        logger.info("WebSocket connection closing: $code - $reason")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        logger.info("WebSocket connection closed: $code - $reason")
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
}