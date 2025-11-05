package com.min.mockstock.application.event

data class UserRegisteredEvent(
        val userId: Long,
        val loginId: String
)
