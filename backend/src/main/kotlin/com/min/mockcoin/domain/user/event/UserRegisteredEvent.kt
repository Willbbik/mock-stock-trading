package com.min.mockcoin.domain.user.event

data class UserRegisteredEvent(
        val userId: Long,
        val loginId: String
)
