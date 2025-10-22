package com.min.mockcoin.domain.coin

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "TB_COIN_TRADE_HIST")
data class CoinTradeHist(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column
        val market: String,

        @Column
        val buyPrice: Double,

        @Column
        val buyQuantity: Int,

        @Column
        val trdType: String,

        @Column(insertable = false, updatable = false)
        val buyDate: LocalDateTime? = null,
)
