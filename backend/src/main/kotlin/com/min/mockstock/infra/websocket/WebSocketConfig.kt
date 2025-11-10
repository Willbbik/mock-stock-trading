package com.min.mockstock.infra.websocket

import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import java.util.concurrent.TimeUnit

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
//        registry.setApplicationDestinationPrefixes("/app")
    }

    @Bean("koreaTradeClient")
    fun koreaTradeClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .pingInterval(5, TimeUnit.SECONDS) // 5초마다 ping 전송해서 연결 유지
            .build()
    }

}