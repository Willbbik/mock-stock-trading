package com.min.mockstock.infra.websocket.dto

data class KoreaTradeWebSocketRequest(
    val header: Header,
    val body: Body
) {
    data class Header(
        val approval_key: String,
        val custtype: String,
        val tr_type: String,
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
