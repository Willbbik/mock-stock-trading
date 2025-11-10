package com.min.mockstock.domain.stock

import com.min.mockstock.domain.shared.CurrencyType
import jakarta.persistence.*

@Entity
@Table(name = "stock") // 주식 종목 정보
class Stock(
    stockCode: String,
    stockName: String,
    marketType: MarketType,
    currency: CurrencyType,
    isUse: Boolean = true
) {

    @Id
    @Column(name = "stock_code") // 종목 코드
    var stockCode: String = stockCode
        protected set

    @Column(name = "stock_name", nullable = false) // 종목명
    var stockName: String = stockName
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "market_type", nullable = false) // 시장 구분
    var marketType: MarketType = marketType
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false) // 통화 화폐
    var currency: CurrencyType = currency
        protected set

    @Column(name = "is_use", nullable = false) // 사용 여부
    var isUse: Boolean = isUse
        protected set
}