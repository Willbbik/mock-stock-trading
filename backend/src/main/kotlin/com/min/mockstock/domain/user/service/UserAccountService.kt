package com.min.mockstock.domain.user.service

import com.min.mockstock.domain.user.model.UserAccount
import com.min.mockstock.infra.properties.UserAccountProperties
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserAccountService(
    private val userAccountProperties: UserAccountProperties
) {

    // 새로운 사용자 계좌 생성
    fun createNewAccount(userId: String?) : UserAccount{

        val defaultMoney = userAccountProperties.defaultBalance // 초기 자본금 설정
        val accountNumber = createAccountNumber()

        // 사용자 계좌 생성
        return UserAccount(
            userId = userId ?:"",
            accountNumber = accountNumber,
            totalAsset = defaultMoney,
            depositCash = defaultMoney
        )
    }

    private fun createAccountNumber() : String {
        return UUID.randomUUID().toString()
    }

}