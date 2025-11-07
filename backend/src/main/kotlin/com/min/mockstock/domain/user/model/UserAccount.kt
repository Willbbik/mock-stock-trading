package com.min.mockstock.domain.user.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_account") // 사용자 주식 계좌
data class UserAccount (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id") // 계좌 ID
    val accountId: Long = 0,

    @Column(name = "user_id", nullable = false) // 사용자 ID
    val userId: Long,

    @Column(name = "total_asset", nullable = false) // 총 자산 (현금 + 주식 평가액)
    val totalAsset: Double = 0.0,

    @Column(name = "deposit_cash", nullable = false) // 현금
    val depositCash: Double = 0.0,

    @Column(name = "is_active", nullable = false) // 계좌 활성화 여부
    val isActive: Boolean = true,

    @Column(name = "created_at", insertable = false, updatable = false) // 생성일시
    val createdAt: LocalDateTime? = null
)