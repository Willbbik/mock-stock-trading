package com.min.mockstock.domain.user.model

import com.min.mockstock.domain.shared.CustomId
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    loginId: String,
    password: String,
    name: String,
    email: String
) {

    @Id
    @CustomId
    @Column(name = "id")
    lateinit var userId: String
        protected set

    @Column(name = "login_id", nullable = false)
    var loginId: String = loginId
        protected set

    @Column(name = "password", nullable = false)
    var password: String = password
        protected set

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "email", nullable = false)
    var email: String = email
        protected set

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}