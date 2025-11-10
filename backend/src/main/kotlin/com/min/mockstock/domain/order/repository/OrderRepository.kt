package com.min.mockstock.domain.order.repository;

import com.min.mockstock.domain.order.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, String> {
}