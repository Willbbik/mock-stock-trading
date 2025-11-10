package com.min.mockstock.domain.order.model

enum class OrderStatus(val desc: String) {
    REQUESTED("주문 요청"),
    CANCELLED("주문 취소"),
    EXECUTED("체"),
    FAILED("체결 실패"),
}