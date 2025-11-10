package com.min.mockstock.application.command

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TradeCommandService(
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun order() {
        /**
         * 1. 종목 코드 검증
         * 2. 잔고 검증
         * 3. 거래내역 생성
         * 4. 잔고 업데이트
         * 5. (옵션) 알람
         *
         */

    }

}