package com.min.mockcoin.infrastructure.upbit.result

import com.fasterxml.jackson.annotation.JsonProperty

data class UpbitMarketInfo(
    @JsonProperty("market")
    val market: String?,

    @JsonProperty("korean_name")
    val koreanName: String?,

    @JsonProperty("english_name")
    val englishName: String?
)