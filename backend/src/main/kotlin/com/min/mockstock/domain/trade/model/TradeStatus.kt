package com.min.mockstock.domain.trade.model

enum class TradeStatus(val desc: String) {
    EXECUTED("체결 완료"),
    FAILED("체결 실패"),
    CANCELLED("거래 취소");
}