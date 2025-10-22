package com.min.mockstock.domain.user.dto

import org.jetbrains.annotations.NotNull

data class LoginDTO(
        @field:NotNull
        val loginId: String,

        @field:NotNull
        val password: String
)
