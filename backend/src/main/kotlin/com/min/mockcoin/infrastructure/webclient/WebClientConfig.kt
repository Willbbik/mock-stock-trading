package com.min.mockcoin.infrastructure.webclient

import com.min.mockcoin.infrastructure.properties.UpbitProperties
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
        private val upbitProperties: UpbitProperties
) {

        @Bean
        @Qualifier("upbitWebClient")
        fun upbitWebClient(): WebClient {
            return WebClient.builder()
                    .baseUrl(upbitProperties.baseUrl)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .build()
        }

}