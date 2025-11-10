package com.min.mockstock.domain.order.model

enum class OrderMethod(val desc: String) {
    MARKET("시장가"),
    LIMIT("지정가")
}