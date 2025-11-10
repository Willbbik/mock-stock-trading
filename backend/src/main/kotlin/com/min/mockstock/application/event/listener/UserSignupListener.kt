package com.min.mockstock.application.event.listener

import com.min.mockstock.application.event.UserSignupEvent
import com.min.mockstock.domain.user.repository.UserAccountRepository
import com.min.mockstock.domain.user.service.UserAccountService
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserSignupListener(
    val userAccountService: UserAccountService,
    val userAccountRepository: UserAccountRepository
) {

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    @Transactional
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handleEvent(event: UserSignupEvent) {
        // 새로운 사용자 계좌 생성 및 저장
        userAccountService.createNewAccount(event.userId).let { userAccountRepository.save(it) }
    }



}