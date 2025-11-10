package com.min.mockstock.domain.user.model

import com.min.mockstock.domain.shared.CustomId
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "user_account")
class UserAccount(
    userId: String,
    accountNumber: String,
    totalAsset: BigDecimal,
    depositCash: BigDecimal
) {

    @Id
    @CustomId
    @Column(name = "account_id")
    lateinit var accountId: String
        protected set

    @Column(name = "user_id", nullable = false)
    var userId: String = userId
        protected set

    @Column(name = "account_number", nullable = false)
    var accountNumber: String = accountNumber
        protected set

    @Column(name = "total_asset", nullable = false)
    var totalAsset: BigDecimal = totalAsset
        protected set

    @Column(name = "deposit_cash", nullable = false)
    var depositCash: BigDecimal = depositCash
        protected set

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = true
        protected set

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

}