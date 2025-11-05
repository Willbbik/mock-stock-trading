package com.min.mockstock.infrastructure.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.min.mockstock.infra.websocket.dto.KoreaTradeWebSocketRequest
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate

class KoreaTradeWebSocketListener(
    private val objectMapper: ObjectMapper,
    private val properties: KoreaTradeWebSocketProperties
) : WebSocketListener() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun onOpen(webSocket: WebSocket, response: Response) {
        logger.info("WebSocket connection opened")

        val request = createSubscribeMessage("005930") // TODO: Make stock code configurable
        webSocket.send(request)
        logger.info("Sent subscription message")
    }

    override fun onMessage(webSocket: WebSocket, bytes: okio.ByteString) {
        try {
            val message = bytes.utf8()
            logger.debug("Received message: $message")
            // TODO: Process the message and send to Kafka
            // val ticker = objectMapper.readValue(message, YourTickerClass::class.java)
            // kafkaTemplate.send("your-topic", objectMapper.writeValueAsString(ticker))
        } catch (e: Exception) {
            logger.error("Error processing WebSocket message", e)
        }
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        logger.error("WebSocket connection failed: ${t.message}", t)
        // TODO: Implement reconnection logic
    }

    private fun createSubscribeMessage(stockCode: String): String {
        val request = KoreaTradeWebSocketRequest(
            header = KoreaTradeWebSocketRequest.Header(
                approval_key = properties.approvalKey,
                custtype = properties.custType,
                tr_type = properties.trType,
                content_type = properties.contentType
            ),
            body = KoreaTradeWebSocketRequest.Body(
                input = KoreaTradeWebSocketRequest.Input(
                    tr_id = properties.trId,
                    tr_key = stockCode
                )
            )
        )
        return objectMapper.writeValueAsString(request)
    }
}
