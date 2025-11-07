package com.min.mockstock.domain.user.service

import com.min.mockstock.domain.user.model.UserAccount
import com.min.mockstock.infra.properties.UserAccountProperties
import org.springframework.stereotype.Service

@Service
class UserAccountService(
    private val userAccountProperties: UserAccountProperties
) {

    // 새로운 사용자 계좌 생성
    fun createNewAccount(userId: Long) : UserAccount{

        val defaultMoney = userAccountProperties.defaultBalance // 초기 자본금 설정

        // 사용자 계좌 생성
        return UserAccount(
            userId = userId,
            totalAsset = defaultMoney,
            depositCash = defaultMoney
        )
    }

}