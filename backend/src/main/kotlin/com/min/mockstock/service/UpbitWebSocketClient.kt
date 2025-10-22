package com.min.mockstock.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.min.mockstock.domain.upbit.UpbitKafkaTopic
import com.min.mockstock.domain.upbit.UpbitMarkets
import com.min.mockstock.domain.upbit.dto.UpbitTicker
import jakarta.annotation.PostConstruct
import okhttp3.*
import okhttp3.WebSocketListener
import okio.ByteString
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class UpbitWebSocketClient (
        private val kafkaTemplate: KafkaTemplate<String, String>,
        private val objectMapper: ObjectMapper
)
{

    @PostConstruct
    fun connectToUpbit() {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("wss://api.upbit.com/websocket/v1")
                .build()

        client.newWebSocket(request, listener)
    }

    val listener = object : WebSocketListener() {

        override fun onOpen(webSocket: okhttp3.WebSocket, response: okhttp3.Response) {
            println("WebSocket Opened")

            val param = listOf(
                mapOf(
                    "ticket" to "test"
                ),
                mapOf(
                    "type" to "ticker",
                    "codes" to UpbitMarkets.allMarkets
                )
            )

            val subscribeMsg = Gson().toJson(param)
            webSocket.send(subscribeMsg)

            println("WebSocket send")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            val ticker: UpbitTicker = objectMapper.readValue(bytes.utf8(), UpbitTicker::class.java)

            kafkaTemplate.send(UpbitKafkaTopic.TICKER, objectMapper.writeValueAsString(ticker))
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            println("❌ 오류 발생: ${t.message}")
        }
    }

}