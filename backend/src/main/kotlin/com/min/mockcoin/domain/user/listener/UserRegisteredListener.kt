package com.min.mockcoin.domain.user.listener

import com.min.mockcoin.domain.user.UserAccount
import com.min.mockcoin.domain.user.event.UserRegisteredEvent
import com.min.mockcoin.domain.user.repository.UserAccountRepository

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserRegisteredListener(
        val userAccountRepository: UserAccountRepository
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handleEvent(event: UserRegisteredEvent) {

        // 사용자 계좌 생성
        UserAccount(userId = event.userId).let { userAccountRepository.save(it) }
    }

}