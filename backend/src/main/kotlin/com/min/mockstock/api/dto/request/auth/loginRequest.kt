package com.min.mockstock.api.dto.request.auth

import org.jetbrains.annotations.NotNull

data class loginRequest(
        @field:NotNull
        val loginId: String,

        @field:NotNull
        val password: String
)
