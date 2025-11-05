package com.min.mockstock.domain.user.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_account")
data class UserAccount (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val accountId: Long = 0,

    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val totalAsset: Double = 0.0,

    @Column(nullable = false)
    val depositCash: Double = 0.0,

    @Column(nullable = false)
    val isActive: Boolean = true,

    @Column(insertable = false, updatable = false)
    val createdAt: LocalDateTime? = null
)