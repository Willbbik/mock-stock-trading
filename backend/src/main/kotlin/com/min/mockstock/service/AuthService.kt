package com.min.mockstock.service

import com.min.mockstock.domain.user.User
import com.min.mockstock.domain.user.dto.LoginDTO
import com.min.mockstock.domain.user.dto.RegisterUserDTO

interface AuthService {

    fun registerUser(param: RegisterUserDTO)

    fun loginUser(param: LoginDTO): User

}