package com.min.mockstock.infra.websocket.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class KoreaTradeWebSocketRequest(
    val header: Header,
    val body: Body
) {
    data class Header(
//        @JsonProperty("approval-key")
//        val approval_key: String,
        val appkey: String,
        val appsecret: String,
        val custtype: String,
        val tr_type: String,
        @JsonProperty("content-type")
        val content_type: String
    )

    data class Body(
        val input: Input
    )

    data class Input(
        val tr_id: String,
        val tr_key: String
    )
}
