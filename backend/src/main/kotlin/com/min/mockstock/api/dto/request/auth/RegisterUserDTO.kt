package com.min.mockstock.api.dto.request.auth

import jakarta.validation.constraints.NotBlank

data class RegisterUserDTO(

        @field:NotBlank
        val loginId: String,

        @field:NotBlank
        val password: String,

        @field:NotBlank
        val name: String,

        @field:NotBlank
        val email: String
)