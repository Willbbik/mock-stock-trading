package com.min.mockstock.domain.user.event

data class UserRegisteredEvent(
        val userId: Long,
        val loginId: String
)
