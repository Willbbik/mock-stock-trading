package com.min.mockcoin.service

import com.min.mockcoin.domain.user.User
import com.min.mockcoin.domain.user.dto.LoginDTO
import com.min.mockcoin.domain.user.dto.RegisterUserDTO

interface AuthService {

    fun registerUser(param: RegisterUserDTO)

    fun loginUser(param: LoginDTO): User

}