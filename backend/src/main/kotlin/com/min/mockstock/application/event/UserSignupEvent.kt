package com.min.mockstock.application.event

data class UserSignupEvent(
        val userId: Long,
        val loginId: String
)
