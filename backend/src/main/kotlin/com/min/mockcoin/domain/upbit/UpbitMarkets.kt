package com.min.mockcoin.domain.upbit

object UpbitMarkets {

    val krwMarkets = listOf(
            "KRW-XPL",    // 플라즈마
            "KRW-MIRA",   // 미라네트워크
            "KRW-USDT",   // 테더
            "KRW-XRP",    // 리플
            "KRW-ETH",    // 이더리움
            "KRW-SOL",    // 솔라나
            "KRW-BTC",    // 비트코인
            "KRW-AVNT",   // 아반티스
            "KRW-UXLINK", // 유엑스링크
            "KRW-0G",     // 제로지
            "KRW-KAITO",  // 카이토
            "KRW-DOGE",   // 도지코인
            "KRW-IP",     // 스토리
            "KRW-LINEA",  // 리네아
            "KRW-WLFI",   // 월드리버티파이낸셜
            "KRW-FLUID"   // 플루이드
    )

    val btcMarkets = listOf(
            "BTC-SNX",    // 신세틱스
            "BTC-IN",     // 인피넷
            "BTC-SOON",   // 쑨
            "BTC-NMR",    // 누메레르
            "BTC-HAEDAL", // 해달프로토콜
            "BTC-HUMA",   // 후마파이낸스
            "BTC-SHELL",  // 마이쉘
            "BTC-CTC",    // 크레딧코인
            "BTC-IOTX",   // 아이오텍스
            "BTC-USDT",   // 테더
            "BTC-BABY",   // 바빌론
            "BTC-MIRA",   // 미라네트워크
            "BTC-SWELL",  // 스웰네트워크
            "BTC-YGG",    // 일드길드게임즈
            "BTC-ETH"     // 이더리움
    )

    val usdtMarkets = listOf(
            "USDT-BTC",   // 비트코인
            "USDT-IN",    // 인피넷
            "USDT-ETH",   // 이더리움
            "USDT-XRP",   // 리플
            "USDT-SOON",  // 쑨
            "USDT-BRETT", // 브렛
            "USDT-SOL",   // 솔라나
            "USDT-KAITO", // 카이토
            "USDT-DOGE",  // 도지코인
            "USDT-ONDO",  // 온도파이낸스
            "USDT-XPL",   // 플라즈마
            "USDT-MIRA",  // 미라네트워크
            "USDT-TRX",   // 트론
            "USDT-B3",    // 비쓰리
            "USDT-BONK"   // 봉크
    )

    val allMarkets = krwMarkets + btcMarkets + usdtMarkets
}
